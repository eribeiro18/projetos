from agno.agent import Agent
from agno.tools.tavily import TavilyTools
from agno.models.groq import Groq

from dotenv import load_dotenv
load_dotenv()

agent = Agent(
    model=Groq(id="qwen/qwen3.8-27b"),
    tools=[TavilyTools()],
    debug_mode=True
)

agent.print_response("Use suas ferramentas para pesquisar a temperatura de hoje em Santa Rosa de Viterbo")