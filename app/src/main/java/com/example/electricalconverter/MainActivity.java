package com.example.electricalconverter;

import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity {

    private EditText cos;//a
    private EditText P;  //
    private EditText I;  //   добавляем переменные эдит
    private EditText U;//
    private EditText L;
    private TextView IView;
    private TextView PView;
    private Button buttonI;
    private Button buttonP;
    private Button buttonHelp;
    private Button button;


    class ConverterI {

        private double cos; //a
        private double p; //b
        private double kor = Math.sqrt(3);
        private double u;

        double convertI1() {
            double i;
            i = (p * 1000) / (u) / cos;
            return i;
        }

        double convertI3() {
            double i;
            i = (p * 1000) / ((kor * u) * cos);
            return i;
        }
    }



    class ConverterP {
        private double cos; //a
        private double i; //b
        private double kor = Math.sqrt(3);
        private double u;

        double convertP1() {
            double p;
            p = (u * i * cos) / 1000;
            return p;
        }

        double convertP3() {
            double p;
            p = (kor * u * i * cos) / 1000;
            return p;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IView = (TextView) findViewById(R.id.IView);  //прописываем элементы
        PView = (TextView) findViewById(R.id.PView);
        buttonI = (Button) findViewById(R.id.buttonI);
        buttonP = (Button) findViewById(R.id.buttonP);
        button = (Button) findViewById(R.id.button);
        buttonHelp = (Button) findViewById(R.id.buttonHelp);
        cos = (EditText) findViewById(R.id.cos);
        P = (EditText) findViewById(R.id.P);
        U = (EditText) findViewById(R.id.U);
        I = (EditText) findViewById(R.id.I);
        L = (EditText) findViewById(R.id.L);

        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        /*    Toast toast = Toast.makeText(getApplicationContext(),R.string.app_name, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 1, -1);
            LinearLayout toastContainer = (LinearLayout)toast.getView();          // вывод рисунка в сообщение
            ImageView mainImageView = new ImageView(getApplicationContext());
            mainImageView.setImageResource(R.drawable.main);   //вызов рисунка
            toastContainer.addView(mainImageView, 0);
            toast.show();  */

                Toast toast = Toast.makeText(getApplicationContext(),
                        " 1.Введите косинус фи \n" +
                                "(если не известен введите 1) \n" +
                                "2. Введите изветную мощность P (в кВт) \n" +
                                "3. Введите известный ток I (в А) \n" +
                                "4. Введите напряжение U (в Вольтах) \n" +
                                "5. Нажмите ПЕРЕВОД (искомых данных) \n" +
                                "6. При новом расчете " +
                                " нажмите сброс ", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);   // расположение
                toast.show();
            }
        });
        P.addTextChangedListener(pTextWatcher);                                           //проверка едит на ввод
        I.addTextChangedListener(pTextWatcher);
        cos.addTextChangedListener(pTextWatcher);
        U.addTextChangedListener(pTextWatcher);
    }

    private TextWatcher pTextWatcher;

    {
        pTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String PInput = P.getText().toString().trim();                                    //ввод в эдит
                String IInput = I.getText().toString().trim();
                String UInput = U.getText().toString().trim();
                String cosInput = cos.getText().toString().trim();
                buttonI.setEnabled(!PInput.isEmpty() && !cosInput.isEmpty() && !UInput.isEmpty());                      //если едит П и эдит кос пустые отключаем кнопку
                buttonP.setEnabled(!IInput.isEmpty() && !cosInput.isEmpty() && !UInput.isEmpty());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String PInput = P.getText().toString().trim();                                    //ввод в эдит
                String IInput = I.getText().toString().trim();
                String cosInput = cos.getText().toString().trim();
                String UInput = U.getText().toString().trim();
                buttonI.setEnabled(!PInput.isEmpty() && !cosInput.isEmpty() && !UInput.isEmpty());                      //если едит П и эдит кос пустые отключаем кнопку
                buttonP.setEnabled(!IInput.isEmpty() && !cosInput.isEmpty() && !UInput.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
                final String PInput = P.getText().toString().trim();                                    //ввод в эдит
                String IInput = I.getText().toString().trim();
                String cosInput = cos.getText().toString().trim();
                String UInput = U.getText().toString().trim();
                buttonI.setEnabled(!PInput.isEmpty() && !cosInput.isEmpty() && !UInput.isEmpty());                      //если едит П и эдит кос пустые отключаем кнопку
                buttonP.setEnabled(!IInput.isEmpty() && !cosInput.isEmpty() && !UInput.isEmpty());

                buttonI.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ConverterI conv = new ConverterI();
                        double i;
                        String S1 = cos.getText().toString();
                        String S2 = P.getText().toString();
                        String S3 = U.getText().toString();
                        conv.cos = Double.parseDouble(S1);
                        conv.p = Double.parseDouble(S2);
                        conv.u = Integer.parseInt(S3);
                            if (conv.u > 0 & conv.u < 380) {
                                i = conv.convertI1();
                            } else
                                i = conv.convertI3();


                            if (conv.u > 0 & conv.u < 380) {
                                i = conv.convertI1();
                            } else
                                i = conv.convertI3();

                            IView.setText(String.format("% .2f", i));}

                }); //  конец кода клика

                buttonP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ConverterP conv = new ConverterP();
                        double p;
                        String S1 = cos.getText().toString();
                        String S2 = I.getText().toString();
                        String S3 = U.getText().toString();
                        conv.cos = Double.parseDouble(S1);
                        conv.i = Double.parseDouble(S2);
                        conv.u = Double.parseDouble(S3);
                            if (conv.u > 0 & conv.u < 380) {
                                p = conv.convertP1();
                            } else {
                                p = conv.convertP3();
                            }
                            String S = Double.toString(p);
                            PView.setText(String.format("% .2f", p));
                        }
                });

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IView.setText("");
                        PView.setText("");
                        P.getText().clear();
                        I.getText().clear();
                        cos.getText().clear();
                        U.getText().clear();
                    }
                });
            }
        };
    }
}















