package com.sjpproj1.sjp1;


        import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    EditText base1;
    EditText base2;
    EditText num;
    TextView res;
    Button btnCon;



    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().setTitle("Number System Converter App");
        getSupportActionBar().setTitle("Number System Converter App");

        base1= (EditText)findViewById(R.id.txtBase1);
        base2= (EditText)findViewById(R.id.txtBase2);
        num= (EditText)findViewById(R.id.txtNumber);
        res= (TextView)findViewById(R.id.txtResult);
        btnCon= (Button)findViewById(R.id.btnConvert);
        btnCon.setOnClickListener(this) ;

    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnConvert :
                convert();
                break;

        }
    }
    private void convert() {
        double t,n=0,f,k;
        float ans;
        String s1="",hexa_ans="";
        int i,p1,b,x,p2;
        b=Integer.parseInt(base1.getText().toString());
        if((b<2 || b>10)&& b!=16)
        {
            Toast.makeText(this, "Invalid choice:not a valid input base",
                    Toast.LENGTH_LONG).show();
            return;
        }
        x=Integer.parseInt(base2.getText().toString());
        if((x<2 || x>10)&& x!=16)
        {
            Toast.makeText(this, "Invalid choice:not a valid output base",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if(b<=10) {
            try {
                n = Float.parseFloat(num.getText().toString());
            }
            catch (Exception e){
                Toast.makeText(this, "Invalid choice:not a valid digit",
                        Toast.LENGTH_LONG).show();
                return;
            }
        }
        else {
            s1 = num.getText().toString();
        }
        AnyToDec a1=new AnyToDec();
        DecToAny a2= new DecToAny();
        AnyToHexa a3=new AnyToHexa();
        if(b<=10 && x<=10)
        {
            f = a1.NToDec(n,b);
            if (f==-1){
                Toast.makeText(this, "Invalid input:not a digit ",
                        Toast.LENGTH_LONG).show();
                return;

            }
            k = a2.DecTON(f, x);
            ans=(float)k;
            res.setText(String.format("%f",k));
        }
        if(b<=10 && x==16)
        {
            f=a1.NToDec(n,b);
            if(f==-1){
                Toast.makeText(this, "Invalid choice:not a digit",
                        Toast.LENGTH_LONG).show();
                return;

            }
            p1=(int)f;
            hexa_ans=a3.hexa(p1,hexa_ans);
            StringBuffer hexa_ans1=new StringBuffer(hexa_ans);
            hexa_ans1.reverse();
            hexa_ans=hexa_ans1.toString();
            hexa_ans=hexa_ans+".";
            t=(float)(f-p1);
            p2=0;
            for(i=0;i<6;i++)
            {
                if(t!=0)
                {
                    t=t*16;
                    p2=(int)(t);
                    if(p2<10)
                        hexa_ans=hexa_ans+p2;
                    else
                        hexa_ans=hexa_ans+(char)(p2+55);
                    t=(float)(t-p2);
                }
                else hexa_ans=hexa_ans+"0";
            }
            res.setText(hexa_ans);
        }
        if(b==16 && x<=10)
        {
            Toast.makeText(this, "s1 : ",
                    Toast.LENGTH_LONG).show();
            f=a1.HexaToDec(s1);
            if(f==-1){
                Toast.makeText(this, "Invalid choice:not a digit",
                        Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(this, "decimal : " + f,
                    Toast.LENGTH_LONG).show();
            k=a2.DecTON(f, x);
            res.setText(String.format("%f",k));
        }


    }


}
class AnyToDec
{
    double NToDec(double n,int b)
    {
        int u,x,j,p;
        float d=0,k=1,f,y=0;
        x=(int)n;

        f=(float)(n-x);
        while(x!=0)
        {
            u=x%10;
            if(u>b)
            {
                return -1;
            }
            d=d+u*k;
            k=k*b;
            x=x/10;
        }
        f=f*1000000;
        j=(int)f%100;
        p=(int)f/100;
        if(j>=50)
            p=p+1;
        k=1/(float)(Math.pow(b,4));
        while(p!=0)
        {
            u=p%10;
            if(u>b)
                break;
            d=d+u*k;
            p=p/10;
            k=k*b;
        }
        return d;//converted decimal number
    }


    double HexaToDec (String h)//TO CONVERT HEXADEC TO DEC
    {
        int x=h.length();
        int n=-1;
        for(int i=0;i<x;i++)
            if(h.charAt(i)=='.')
                n=h.indexOf('.');

        int m;
        if(n==-1)
            m=x-1;
        else m=n-1;
        double d=0;
        int u;char ch;String s;
        for(int i=0;i<x;i++)
        {
            if(h.charAt(i)=='.')
                continue;
            if(Character.isDigit(h.charAt(i))==true)
            {
                u=(int)h.charAt(i)-48;
                d=d+(float)u*Math.pow(16,m);
                m=m-1;
            }
            else
            {
                u=(int)h.charAt(i)-55;
                if(u<65||u>70)
                {
                    return -1;
                }
                d=d+(float)u*Math.pow(16,m);
                m=m-1;
            }
        }
        return d; //converted decimal number

    }
}
class DecToAny //converts decimal number to equivalent desired base except hexa
{

    double DecTON(double n,int b)
    {
        int m, c=0, f=10,u,p;
        float s;
        double sum=0,j=1;
        m=(int)n;
        s=(float)(n-m);
        while(m!=0)
        {
            u=m%b;

            m=m/b;

            sum=sum+u*j;

            j=j*10;

        }

        while(c<=4)
        {
            s=s*b;
            p=(int)s;
            sum=sum+p/(float)f;
            f=f*10;
            s=(float)(s-p);
            c++;
        }


        return(sum);//returns the converted number

    }
}

class AnyToHexa
{
    String hexa(int x,String hexa_ans)//converts decimal number to equivalent hexadec number
    {
        int d;
        char c;
        if(x==0)
            return "0";
        while(x!=0)
        {
            if(x%16<10)
            {
                d=x%16;
                hexa_ans=hexa_ans.concat(Integer.toString(d));

            }
            else
            {
                c=(char)(x%16+55);
                hexa_ans=hexa_ans.concat(Character.toString(c));
            }
            x=x/16;

        }
        return hexa_ans;

    }
}
