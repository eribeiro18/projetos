from agno.agent import Agent
from agno.tools.tavily import TavilyTools
from agno.models.google import Gemini

from dotenv import load_dotenv
load_dotenv()

#O comentario interno serve para o modelo entender o que a funcao faz e como deve ser acionada
def celsius_to_fh(temperatura_celsius: float):
    """
    Converte uma temperatura de graus Celsius para Fahrenheit.
    Args:
        temperatura_celsius (float): Temperatura em graus Celsius
    Returns:
        float: Temperatura em graus Fahrenheit
    """
    return(temperatura_celsius * 9/5 +32)

agent = Agent(
    model=Gemini(id="gemini-3.8-flash"),
    tools=[
        TavilyTools(),
        celsius_to_fh
        ],
    debug_mode=True
)

#ponto importante para o modelo entender que a funcao celsius_to_fh deve ser chamada, precisa deixar claro no prompt
agent.print_response("Use suas ferramentas para pesquisar a temperatura de hoje em Santa Rosa de Viterbo em Fahrenheit")