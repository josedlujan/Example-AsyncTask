package asyntask.ejemplo.com.ejemploasyntask;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button boton;

    //
    ProgressBar progressBar;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.texto);
        boton = (Button) findViewById(R.id.boton);

        //
        progressBar = (ProgressBar) findViewById(R.id.progresbar);
        progressBar.setVisibility(View.GONE);
        progressBar.setMax(100);
        //


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute(100);
            }
        });

    }



    public class MyAsyncTask extends AsyncTask<Integer, Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //
            progressBar.setVisibility(View.VISIBLE);
            //
        }

        @Override
        protected String doInBackground(Integer... params) {
            int max = params[0];
            for(int i=1; i< max;i++){
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                publishProgress(i);
            }


            return "End";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int contador = values[0];
            String texto = "Contador: "+contador;
            textView.setText(texto);
            textView.setTextSize(contador);
            //
            progressBar.setProgress(values[0]);
            //
        }

        @Override
        protected void onPostExecute(String s) {
            textView.append("\n"+s);
            //
            progressBar.setVisibility(View.INVISIBLE);
            //
        }
    }
}
