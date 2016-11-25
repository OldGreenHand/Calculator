/**
 * Author: Rui Min
 * Created by Ray on 3/04/2016.
 * Main Activity including all responses and functions of buttons
 */
package com.example.ken_and_ray.calculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.ken_and_ray.calculator.Calculate.Calculator;
import com.example.ken_and_ray.calculator.Calculate.StaticTool;
import com.example.ken_and_ray.calculator.ExpressionTree.ExpressionTree;
import com.example.ken_and_ray.calculator.ParsingAlgorithm.Convertor;
import com.example.ken_and_ray.calculator.ParsingAlgorithm.Tokenizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    public String fomula = ""; // String which will be displayed on the screen.
    public String str = ""; // String which will be parsed to be calculated.
    public String lastresult = ""; // To record last result.
    public String history = "";
    public Tokenizer tokenizer;
    public Queue<String> prefix;
    public EditText edit;
    static final String filename = "CalculatorANS";
    FileInputStream inputStream;
    FileOutputStream outputStream;
    File persistentFile;

    //
	public String getResult(String expression) {
		Calculator calculator = new Calculator();
		calculator.initial();
		
		System.out.println("Get input expression: " + expression +
				"\n\n------- Applying tokenizer ------- \n");

        // applying tokenizer with input expression
        try {
            tokenizer = new Tokenizer(expression);
        } catch(Exception e) {
          return StaticTool.ExceptionContent;
        }

		System.out.println("\n\n-----Stat parsing------");

        // applying parsing algorithm for all token
		Convertor convertor = new Convertor();
        try {
            prefix = convertor.getPrefix(new Tokenizer(expression));
        } catch (Exception e) {
            return StaticTool.ExceptionContent;
        }
		
		System.out.print("\n\n----Start evaluate-----\n");
		ExpressionTree expressionTree = new ExpressionTree();
        BigDecimal result = new BigDecimal(0);
        // applying parsing result to building tree and evaluate
        try {
            result = ExpressionTree.evaluateExpression(expressionTree.buildExpressionTree(prefix));
            return String.valueOf(result);
        }catch (Exception e){
            return StaticTool.ExceptionContent;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit=(EditText)findViewById(R.id.editText);
        edit.setInputType(InputType.TYPE_NULL);

        /* Read data from the persistent file */
        persistentFile = new File(getFilesDir(),filename);
        if (persistentFile.exists()){
            try{
                inputStream = openFileInput(filename);
                BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder buffer = new StringBuilder();
                while ((line = input.readLine()) != null) {
                    buffer.append(line);
                }
                history = buffer.toString();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void showResult(View view) {
        
        EditText text = (EditText)findViewById(R.id.editText);
        lastresult = getResult(str);
        fomula = lastresult;
        str = lastresult;

        //Add last result into history
        StaticTool st = new StaticTool();
        if (st.isNumber(lastresult)) {
            history += String.valueOf(lastresult);
            history += "; ";
            System.out.println("showResult: " + history);
            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(history.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showAns(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += String.valueOf(lastresult);
        str += String.valueOf(lastresult);
        String[] tmp = history.split("; ");
        if (tmp.length >= 2) {
            lastresult = tmp[tmp.length - 2];

            //Delete last result out of history and modify last result
            history = "";
            for (int i = 0; i <= tmp.length - 2; i++) {
                history = history + tmp[i] + "; ";
            }
        }
        if (tmp.length == 1) {

            lastresult = "";

            //Delete last result out of history and modify last result
            history = "";
        }
        try {
            System.out.println("showANS2: " + history);
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(history.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showZero(View view) {
        
        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "0";
        str += "0";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showOne(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "1";
        str += "1";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showTwo(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "2";
        str += "2";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showThree(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "3";
        str += "3";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showFour(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "4";
        str += "4";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showFive(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "5";
        str += "5";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showSix(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "6";
        str += "6";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showSeven(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "7";
        str += "7";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showEight(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "8";
        str += "8";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showNine(View view) {

        EditText text = (EditText)findViewById(R.id.editText);

        System.out.println("After clearing: " + fomula);
        fomula += "9";
        str += "9";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showPoint(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += ".";
        str += ".";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showPercentage(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "%";
        str += "%";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showPlus(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "+";
        str += "+";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showMinus(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "-";
        str += "-";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showMultiply(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "×";
        str += "*";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showDivide(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "÷";
        str += "/";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showAc(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula = "";
        str = "";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showDelete(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        int len1 = fomula.length();
        int len2 = str.length();
        if (len1 > 0) {
            fomula = fomula.substring(0, len1 - 1);
        }
        if (len2 > 0) {
            str = str.substring(0, len2 - 1);
        }
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showLeftbracket(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "(";
        str += "(";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showRightbracket(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += ")";
        str += ")";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showPi(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "π";
        str += "3.141592653589793238462643383279502884197";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showSquare(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "²";
        str += "^2";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showSqrt(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "√(";
        str += "SQRT(";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showAbs(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "||(";
        str += "ABS(";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showPower(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "^";
        str += "^";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showFactorial(View view) {
        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "fac(";
        str += "FAC(";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showReciprocal(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "1/(";
        str += "1/(";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showLog(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "lg(";
        str += "LOG(";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showLn(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "ln(";
        str += "LN(";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showE(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "e";
        str += "2.718281828459045235360287471352662";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showSin(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "sin(";
        str += "SIN(";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showCos(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "cos(";
        str += "COS(";
        text.setText(fomula);
        text.setSelection(text.length());
    }

    public void showTan(View view) {

        EditText text = (EditText)findViewById(R.id.editText);
        fomula += "tan(";
        str += "TAN(";
        text.setText(fomula);
        text.setSelection(text.length());
    }

}
