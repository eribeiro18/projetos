import streamlit as st
import pandas as pd

st.set_page_config(
    layout="wide",
    page_title="spotify songs"
)

df = pd.read_csv("01 Spotify.csv")
#st.write(df[df["Stream"] > 1000000000])

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

