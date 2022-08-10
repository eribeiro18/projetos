package android.curso.mediaescolarmvc.fragmets;

import android.content.Context;
import android.curso.mediaescolarmvc.R;
import android.curso.mediaescolarmvc.controller.MediaEscolarController;
import android.curso.mediaescolarmvc.model.MediaEscolar;
import android.curso.mediaescolarmvc.util.IncluirAsyncTask;
import android.curso.mediaescolarmvc.util.UtilMediaEscolar;
import android.curso.mediaescolarmvc.view.MainActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class BimestreDFragment extends Fragment {

    MediaEscolar mediaEscolar;
    MediaEscolarController controller;

    Button btnCalcular;
    EditText editMateria;
    EditText editNotaProva;
    EditText editNotaTrabalho;
    TextView txtResultado;
    TextView txtSituacaoFinal;

    double notaProva;
    double notaTrabalho;
    double media;

    boolean dadosValidados = true;

    Context context;

    View view;

    public BimestreDFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();

        controller = new MediaEscolarController(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_bimestre_d, container, false);

        editMateria = view.findViewById(R.id.editMateria);
        editNotaProva = view.findViewById(R.id.editNotaProva);
        editNotaTrabalho = view.findViewById(R.id.editNotaTrabalho);
        txtResultado = view.findViewById(R.id.txtResultado);
        txtSituacaoFinal = view.findViewById(R.id.txtSituacaoFinal);
        btnCalcular = view.findViewById(R.id.btnCalcular);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (editNotaProva.getText().toString().length() > 0) {
                        notaProva = Double.parseDouble(editNotaProva.getText().toString());

                        if (controller.isNotaInformadaValida(10.0)) {
                            dadosValidados = false;
                            UtilMediaEscolar.showMensagem(context,"Nota inválida!");
                            editNotaProva.requestFocus();

                        } else {
                            dadosValidados = true;
                        }

                    } else {
                        editNotaProva.setError("*");
                        editNotaProva.requestFocus();
                        dadosValidados = false;
                    }

                    if (editNotaTrabalho.getText().toString().length() > 0) {
                        notaTrabalho = Double.parseDouble(editNotaTrabalho.getText().toString());


                        if (controller.isNotaInformadaValida(10.0)) {
                            dadosValidados = false;
                            UtilMediaEscolar.showMensagem(context, "Nota inválida!");
                            editNotaTrabalho.requestFocus();

                        } else {
                            dadosValidados = true;
                        }

                    } else {
                        editNotaTrabalho.setError("*");
                        editNotaTrabalho.requestFocus();
                        dadosValidados = false;
                    }

                    if (editMateria.getText().toString().length() == 0) {
                        editMateria.setError("*");
                        editMateria.requestFocus();
                        dadosValidados = false;
                    }

                    // Após Validação
                    if (dadosValidados) {

                        mediaEscolar = new MediaEscolar();

                        mediaEscolar.setMateria(editMateria.getText().toString());
                        mediaEscolar.setNotaProva(Double.parseDouble(editNotaProva.getText().toString()));
                        mediaEscolar.setNotaTrabalho(Double.parseDouble(editNotaTrabalho.getText().toString()));
                        mediaEscolar.setBimestre("4º Bimestre");

                        media = controller.calcularMedia(mediaEscolar);

                        mediaEscolar.setMediaFinal(media);

                        mediaEscolar.setSituacao(controller.resultadoFinal(media));

                        txtResultado.setText(UtilMediaEscolar.formatarValorDecimal(media));

                        txtSituacaoFinal.setText(mediaEscolar.getSituacao());

                        editNotaProva.setText(UtilMediaEscolar.formatarValorDecimal(notaProva));
                        editNotaTrabalho.setText(UtilMediaEscolar.formatarValorDecimal(notaTrabalho));

                        if(controller.salvar(mediaEscolar)){
                            // obj salvo com sucesso no DB
                            UtilMediaEscolar.showMensagem(context,"Dados Salvos com Sucesso...");


                            IncluirAsyncTask task = new
                                    IncluirAsyncTask(mediaEscolar, context);

                            task.execute();
                        }else{
                            // falha ao salvar o obj  no DB
                            UtilMediaEscolar.showMensagem(context,"Falha ao salvar os dados do DB...");
                        }
                    }

                } catch (Exception e) {

                    UtilMediaEscolar.showMensagem(context, "Informe as notas...");

                }

            }
        });

        return view;
    }

}
