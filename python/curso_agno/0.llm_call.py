#from agno.models.openai import OpenAIChat
from agno.models.groq import Groq
from agno.models.message import Message

from dotenv import load_dotenv
load_dotenv()

model = Groq(id="qwen/qwen3.8-27b")

msg = Message(
    role="user",
    content=[{"type": "text", "text": "Olá meu nome é Evandro"}]
)

response = model.response([msg])

print(response.content)