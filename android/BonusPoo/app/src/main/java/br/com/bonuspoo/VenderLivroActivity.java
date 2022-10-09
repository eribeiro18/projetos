package br.com.bonuspoo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import br.com.bonuspoo.model.Livro;

public class VenderLivroActivity extends AppCompatActivity {

    Button btnVenderLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender_livro);
        btnVenderLivro = findViewById(R.id.btnVenderLivro);
        btnVenderLivro.setOnClickListener(view -> {
            Livro vendaLivro = venderLivro();
            String msg = " ISBN: " + vendaLivro.getISBN() + "\n";
            msg += " Nome: " + vendaLivro.getNomeDoLivro() + "\n";
            msg += " Tema: " + vendaLivro.getTema() + "\n";
            msg += " Editora: " + vendaLivro.getEditora() + "\n";
            msg += " Autor: " + vendaLivro.getAutor() + "\n";
            msg += " Num Paginas: " + vendaLivro.getNumeroDePaginas() + "\n";
            msg += " Ano: " + vendaLivro.getAno() + "\n";
            msg += " Edição: " + vendaLivro.getEditora() + "\n";
            msg += " preço de Venda: " + vendaLivro.getPrecoDeVenda() + "\n";
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        });
    }

    public Livro venderLivro(){
        Livro livro = new Livro();
        livro.setISBN("978-3-16-148410-0");
        livro.setNomeDoLivro("Como ficar rico");
        livro.setTema("autoajuda");
        livro.setEditora("Editora Vamos Aprender");
        livro.setAutor("Carlos de Matos");
        livro.setNumeroDePaginas(350);
        livro.setAno(2013);
        livro.setEdicao("1ª Edição");
        livro.setPrecoDeVenda(79.9);
        return livro;
    }
}