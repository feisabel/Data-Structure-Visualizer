Para abrir o DSVisualizer siga os seguintes passos:

1. Baixe os arquivos da biblioteca externa:
https://www.dropbox.com/sh/v5tmmywf1djaxc0/AAAoCshGKlY9Fu880bZIt2kWa?dl=0

2. Para criar um projeto no Eclipse que use a biblioteca externa:
	File -> New -> Java Project 
		Insira um nome para o projeto no espaço "Project name"
		Marque a opção JavaSE-1.7 no campo "Use an execution environment JRE"
	Aperte em "next", em Java Settings:
		Libraries -> Add Externa JARs... -> selecione os arquivos baixados no tópico 1
	Aperte em "finish"
	
3. Para importar os arquivos:
No project explorer:
	Aperte como botão direito no projeto que você criou.
	Import -> File system -> selecione "ds-visualizer" -> ok
	Selecione todos os arquivos e aperte em "finish"

4. Dentre os arquivos importados, execute: GUI.java

5. O manual do usuário encontra-se dentro da pasta help.
