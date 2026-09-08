import tempfile
from typing import Optional, Tuple

import streamlit as st
from langchain.memory import ConversationBufferMemory
from langchain.prompts import ChatPromptTemplate

from langchain_groq import ChatGroq
from langchain_openai import ChatOpenAI

from loaders import *  # mantém sua API atual

# -----------------------------
# Configurações
# -----------------------------
TIPOS_ARQUIVOS_VALIDOS = ["Site", "Youtube", "Pdf", "Csv", "Txt"]

CONFIG_MODELOS = {
    "Groq": {
        "modelos": ["llama-3.1-70b-versatile", "gemma2-9b-it", "mixtral-8x7b-32768"],
        "chat": ChatGroq,
        "help": "Modelos rápidos e custo-efetivos; bons para contexto médio/alto.",
    },
    "OpenAI": {
        "modelos": ["gpt-4o-mini", "gpt-4o", "o1-preview", "o1-mini"],
        "chat": ChatOpenAI,
        "help": "Modelos multimodais e de alta qualidade; 4o é equilibrado, 4o-mini econômico.",
    },
}

# Cria uma memória vazia por padrão (não reutiliza a instância global)
DEFAULT_MEMORY_FACTORY = lambda: ConversationBufferMemory(return_messages=True)


# -----------------------------
# Utilidades
# -----------------------------
@st.cache_data(show_spinner=False)
def _carrega_site_cache(url: str):
    return carrega_site(url)


@st.cache_data(show_spinner=False)
def _carrega_youtube_cache(url: str):
    return carrega_youtube(url)


@st.cache_data(show_spinner=False)
def _carrega_bytes_em_tempfile_cache(kind: str, content_bytes: bytes) -> str:
    """Salva bytes num arquivo temp e retorna o caminho. Usa cache para evitar recarregamentos.
    O cache é por conteúdo (hash interno do Streamlit)."""
    suffix_map = {
        "Pdf": ".pdf",
        "Csv": ".csv",
        "Txt": ".txt",
    }
    suffix = suffix_map.get(kind, ".bin")
    with tempfile.NamedTemporaryFile(suffix=suffix, delete=False) as tmp:
        tmp.write(content_bytes)
        return tmp.name


@st.cache_data(show_spinner=False)
def _carrega_documento_por_caminho(tipo_arquivo: str, caminho: str):
    if tipo_arquivo == "Pdf":
        return carrega_pdf(caminho)
    if tipo_arquivo == "Csv":
        return carrega_csv(caminho)
    if tipo_arquivo == "Txt":
        return carrega_txt(caminho)
    raise ValueError(f"Tipo de arquivo não suportado para caminho: {tipo_arquivo}")


def carrega_arquivos(tipo_arquivo: str, arquivo) -> str:
    """Retorna o texto do documento (str). Trata URL vs upload e usa cache quando possível."""
    if tipo_arquivo == "Site":
        if not arquivo:
            raise ValueError("Informe a URL do site.")
        return _carrega_site_cache(arquivo)

    if tipo_arquivo == "Youtube":
        if not arquivo:
            raise ValueError("Informe a URL do vídeo.")
        return _carrega_youtube_cache(arquivo)

    if tipo_arquivo in ("Pdf", "Csv", "Txt"):
        if not arquivo:
            raise ValueError("Faça o upload do arquivo.")
        # Lê bytes uma única vez
        content_bytes = arquivo.read()
        if not content_bytes:
            raise ValueError("Arquivo vazio ou inválido.")
        caminho = _carrega_bytes_em_tempfile_cache(tipo_arquivo, content_bytes)
        return _carrega_documento_por_caminho(tipo_arquivo, caminho)

    raise ValueError(f"Tipo de arquivo inválido: {tipo_arquivo}")


def montar_system_message(tipo_arquivo: str, documento: str) -> str:
    return (
        "Você é um assistente amigável chamado Oráculo.\n"
        "Você possui acesso às seguintes informações vindas de um documento {tipo}:\n\n"
        "####\n{doc}\n####\n\n"
        "Utilize as informações fornecidas para basear as suas respostas.\n\n"
        "Sempre que houver $ na sua saída, substita por S.\n\n"
        "Se a informação do documento for algo como \"Just a moment...Enable JavaScript and cookies to continue\"\n"
        "sugira ao usuário carregar novamente o Oráculo!"
    ).format(tipo=tipo_arquivo, doc=documento)


def inicializar_chain(provedor: str, modelo: str, api_key: str, documento_texto: str):
    if not api_key:
        raise ValueError(f"Adicione a API key do provedor {provedor}.")

    system_message = montar_system_message(provedor if provedor in ["Site", "Youtube", "Pdf", "Csv", "Txt"] else "Documento", documento_texto)

    template = ChatPromptTemplate.from_messages(
        [
            ("system", system_message),
            ("placeholder", "{chat_history}"),
            ("user", "{input}"),
        ]
    )

    chat_cls = CONFIG_MODELOS[provedor]["chat"]
    chat = chat_cls(model=modelo, api_key=api_key)

    # LangChain Expression Language: template | chat
    chain = template | chat
    return chain


# -----------------------------
# UI: Página de Chat
# -----------------------------

def pagina_chat():
    st.header("🤖 Bem-vindo ao Oráculo", divider=True)

    chain = st.session_state.get("chain")
    if chain is None:
        st.error("Carregue o Oráculo na barra lateral.")
        st.stop()

    memoria: ConversationBufferMemory = st.session_state.get("memoria")
    if memoria is None:
        memoria = DEFAULT_MEMORY_FACTORY()
        st.session_state["memoria"] = memoria

    # Render histórico
    for msg in memoria.chat_memory.messages:
        chat = st.chat_message("human" if msg.type == "human" else "ai")
        chat.markdown(msg.content)

    # Entrada do usuário
    input_usuario = st.chat_input("Fale com o Oráculo")
    if input_usuario:
        # Mostra a mensagem do usuário
        st.chat_message("human").markdown(input_usuario)

        # Espaço para stream da resposta
        ai_box = st.chat_message("ai")
        placeholder = ai_box.empty()

        acumulado = []
        try:
            for chunk in st.session_state["chain"].stream(
                {
                    "input": input_usuario,
                    "chat_history": memoria.chat_memory.messages,
                }
            ):
                content = getattr(chunk, "content", None) or ""
                if content:
                    acumulado.append(content)
                    # Atualiza o placeholder com o texto acumulado
                    placeholder.markdown("".join(acumulado))

            resposta_final = "".join(acumulado)
        except Exception as e:
            resposta_final = f"Desculpe, ocorreu um erro durante o streaming: {e}"
            placeholder.error(resposta_final)

        # Atualiza memória após completar
        memoria.chat_memory.add_user_message(input_usuario)
        memoria.chat_memory.add_ai_message(resposta_final)
        st.session_state["memoria"] = memoria


# -----------------------------
# UI: Sidebar
# -----------------------------

def sidebar():
    tabs = st.tabs(["Upload de Arquivos", "Seleção de Modelos", "Opções"]) 

    with tabs[0]:
        tipo_arquivo = st.selectbox("Selecione o tipo de arquivo", TIPOS_ARQUIVOS_VALIDOS)
        arquivo = None
        if tipo_arquivo == "Site":
            arquivo = st.text_input("Digite a URL do site")
        elif tipo_arquivo == "Youtube":
            arquivo = st.text_input("Digite a URL do vídeo")
        elif tipo_arquivo == "Pdf":
            arquivo = st.file_uploader("Faça o upload do arquivo PDF", type=["pdf"])  # extensão sem ponto
        elif tipo_arquivo == "Csv":
            arquivo = st.file_uploader("Faça o upload do arquivo CSV", type=["csv"])  # extensão sem ponto
        elif tipo_arquivo == "Txt":
            arquivo = st.file_uploader("Faça o upload do arquivo TXT", type=["txt"])  # extensão sem ponto

    with tabs[1]:
        provedor = st.selectbox(
            "Selecione o provedor do modelo",
            list(CONFIG_MODELOS.keys()),
            help="\n".join(f"{k}: {v['help']}" for k, v in CONFIG_MODELOS.items()),
        )
        modelo = st.selectbox("Selecione o modelo", CONFIG_MODELOS[provedor]["modelos"])
        api_key = st.text_input(
            f"API key do provedor {provedor}",
            value=st.session_state.get(f"api_key_{provedor}", ""),
            type="password",
            help="Sua chave é usada apenas localmente para autenticar as requisições à API.",
        )
        st.session_state[f"api_key_{provedor}"] = api_key

    with tabs[2]:
        st.markdown("**Memória da conversa**")
        col1, col2 = st.columns(2)
        with col1:
            if st.button("Apagar histórico", use_container_width=True):
                st.session_state["memoria"] = DEFAULT_MEMORY_FACTORY()
                st.success("Histórico apagado.")
        with col2:
            if st.button("Apagar histórico e reiniciar chain", use_container_width=True):
                st.session_state["memoria"] = DEFAULT_MEMORY_FACTORY()
                st.session_state.pop("chain", None)
                st.success("Memória e chain reinicializadas.")

    # Ações principais
    colA, colB = st.columns([2, 1])
    with colA:
        if st.button("Inicializar Oráculo", use_container_width=True):
            try:
                documento = carrega_arquivos(tipo_arquivo, arquivo)
                if not documento or not documento.strip():
                    st.error("Documento vazio ou ilegível. Verifique o arquivo/URL.")
                    st.stop()

                chain = inicializar_chain(provedor, modelo, api_key, documento)
                st.session_state["chain"] = chain
                st.success("Oráculo inicializado com sucesso! Você já pode conversar na aba principal.")

            except Exception as e:
                st.error(f"Falha ao inicializar: {e}")

    with colB:
        st.caption("Dica: re-inicialize se trocar de arquivo/modelo.")


# -----------------------------
# App principal
# -----------------------------

def main():
    st.set_page_config(page_title="Oráculo", page_icon="🧙", layout="wide")
    with st.sidebar:
        sidebar()
    pagina_chat()


if __name__ == "__main__":
    main()

