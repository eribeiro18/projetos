import streamlit as st
from streamlit_option_menu import option_menu

# --- Configuração da página ---
st.set_page_config(page_title="Dashboard Admin", page_icon="🚀", layout="wide")

# --- Tema Dark/Light ---
if "dark_mode" not in st.session_state:
    st.session_state.dark_mode = False

# --- CSS customizado ---
def custom_css():
    st.markdown(f"""
        <style>
            .topbar {{
                background-color: {"#212529" if st.session_state.dark_mode else "#0d6efd"};
                color: white;
                padding: 15px;
                font-size: 20px;
                font-weight: bold;
                text-align: center;
            }}
            .stApp {{
                background-color: {"#121212" if st.session_state.dark_mode else "white"};
                color: {"white" if st.session_state.dark_mode else "black"};
            }}
            .stMarkdown, .stText, .stHeader, .stSubheader {{
                color: {"white" if st.session_state.dark_mode else "black"} !important;
            }}
        </style>
    """, unsafe_allow_html=True)

custom_css()

# --- Barra superior ---
st.markdown('<div class="topbar">⚡ Dashboard Profissional</div>', unsafe_allow_html=True)

# --- Menu lateral com ícones ---
with st.sidebar:
    st.title("📌 Navegação")
    escolha = option_menu(
        menu_title=None,
        options=["Home", "Relatórios", "Configurações"],
        icons=["house", "bar-chart", "gear"],
        menu_icon="cast",
        default_index=0,
        styles={
            "container": {"padding": "5px", "background-color": "#f8f9fa"},
            "nav-link": {"font-size": "16px", "text-align": "left", "margin": "2px"},
            "nav-link-selected": {"background-color": "#0d6efd", "color": "white"},
        }
    )

    # Switch Dark/Light Mode
    st.markdown("---")
    st.session_state.dark_mode = st.checkbox("🌙 Dark Mode", value=st.session_state.dark_mode)

# --- Conteúdo central ---
if escolha == "Home":
    st.header("🏠 Página Inicial")
    st.write("Bem-vindo ao painel de administração profissional!")
    st.metric("Usuários Ativos", 1234, "+5%")
    st.metric("Vendas Hoje", "R$ 5.230", "+12%")

elif escolha == "Relatórios":
    st.header("📊 Relatórios")
    st.subheader("Vendas por mês")
    st.line_chart({"Vendas": [100, 200, 300, 250, 400, 500]})
    st.subheader("Clientes ativos")
    st.bar_chart({"Clientes": [10, 20, 15, 25, 30, 45]})

elif escolha == "Configurações":
    st.header("⚙️ Configurações")
    usuario = st.text_input("👤 Nome do usuário:")
    st.selectbox("🌍 Idioma", ["Português", "Inglês", "Espanhol"])
    st.write(f"Usuário configurado: {usuario if usuario else 'Nenhum'}")
