import streamlit as st
import pandas as pd
import time as tm

st.set_page_config(
    layout="wide",
    page_title="spotify songs"
)

#isso para operações pesadas, não será executada toda vez irá executar uma unica vez e fazer um cache dos dados
@st.cache_data
def load_data():
    df = pd.read_csv("01 Spotify.csv")
    tm.sleep(5) #no primeiro carregamento ficará esperando por 5 segundos e nas proximas atualizações não irá carregar
    return df

#exibe uma grid com os stream maior que 1M
#st.write(df[df["Stream"] > 1000000000])

df = load_data()

#guarda na sessão do contexto do usuario para reutlizar em outras pages
st.session_state["df_spotify"] = df

df.set_index("Track", inplace=True)

artists = df["Artist"].value_counts().index
# st.sidebar abaixo poderia ser sem o sidebar que ficaria todo em um contexto só
artist = st.sidebar.selectbox("Artistas", artists)
df_filtered = df[df["Artist"] == artist]

albuns = df_filtered["Album"].value_counts().index
album = st.selectbox("Album", albuns)

df_filtered2 = df[df["Album"] == album]

col1, col2 = st.columns(2)

col1.bar_chart(df_filtered2["Stream"])
col2.line_chart(df_filtered2["Danceability"])


#Disposição das coluna em porcentagem exemplo abaixo coluna 1 70% e coluna 2 30%
col1, col2 = st.columns([0.7, 0.3])

col1.bar_chart(df_filtered2["Stream"])
col2.line_chart(df_filtered2["Danceability"])

st.write(artist)

