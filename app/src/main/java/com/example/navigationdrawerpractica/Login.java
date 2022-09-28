package com.example.navigationdrawerpractica;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationdrawerpractica.DataBase.DBHelper;
import com.example.navigationdrawerpractica.Entidades.Vendedor;
import com.example.navigationdrawerpractica.Interfaces.MainActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends AppCompatActivity  {

    EditText user;
    EditText password;
    Button iniciar;
    Button mostrar;
    String usuario ;
    String clave ;
    Connection connection;
    String nombre_DB = "DB_MOBILE";
    Vendedor vendedor = new Vendedor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        user = ((EditText) findViewById(R.id.txtuser));
        password = ((EditText) findViewById(R.id.txtpass));
        iniciar = (Button) findViewById(R.id.enviar_button);


        //quemar datos para iniciar que existen en bd
        user.setText("1060");
        password.setText("*1060");


        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = user.getText().toString();
                clave   = password.getText().toString();

                // validar inicio de secion
                Ingresar_al_sistema(user.getText().toString(),password.getText().toString());

            }
        });
    }



    private void Ingresar_al_sistema(String usuariol, String clavel){

        /*Creamos un objeto de la clase DBHelper e
        instanciamos el constructor y damos el nonbre de
         la base de datos y la version*/
        DBHelper admin=new DBHelper(this,nombre_DB,null,1);
        /*Abrimos la base de datos como escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        /*Creamos dos variables string y capturamos los datos
         ingresado por el usuario y lo convertimos a string*/
        /*inicializamos al cursor y llamamos al objeto de la base
        de datos para realizar un sentencia query where donde
         pasamos las dos variables nombre de usuario y password*/
        Cursor fila = db.rawQuery("select username,clave_user from userstable where username='" +
                usuariol + "' and clave_user='" + clavel + "'", null);
        /*Realizamos un try catch para captura de errores*/
        try {
            /*Condicional if preguntamos si cursor tiene algun dato*/
            if(fila.moveToFirst()){
                //capturamos los valores del cursos y lo almacenamos en variable
                String usua=fila.getString(0);
                String pass=fila.getString(1);
                //preguntamos si los datos ingresados son iguales
                if (usuariol.equals(usua)&&clavel.equals(pass)){
                    //si son iguales entonces vamos a otra ventana
                    //Menu es una nueva actividad empty
                    /////Intent ven=new Intent(this, PrincipalMenu.class);
                    //lanzamos la actividad
                    ////// startActivity(ven);

                    vendedor.setVendedor(user.getText().toString());
                    //limpiamos las las cajas de texto
                    user.setText("ingreso local");
                    password.setText("");

                    //ACA DEBEMOS ENVIAR A LA OTRA VENTANA
                    Intent intent= new Intent (Login.this, MainActivity.class);
                    startActivity(intent);
                }
            }//si la primera condicion no cumple entonces que envie un mensaje toast
            else {
                //CREAMOS LA CONEXION A LA BASE DE DATOS REAL, BORRAMOS LA TABLA DE USUARIOS Y AGREGAMOS ESTE NUEVO USUARIO

                ConSQL c = new ConSQL();
                connection = c.conclass();
                if(c != null){
                    //logear en real
                    try {
                        String sqlstatement = "select * from F_users_mobile_app where vendedor =? and clave =? and estado =? ";
                        PreparedStatement preparedStatement = connection.prepareStatement(sqlstatement);
                        preparedStatement.setString(1, user.getText().toString());
                        preparedStatement.setString(2,password.getText().toString());
                        preparedStatement.setString(3,"A");
                        ResultSet set = preparedStatement.executeQuery();

                        if (set.next()){
                            Toast.makeText(getApplicationContext(), "Felicidades. encontrado", Toast.LENGTH_SHORT).show();
                            //setContentView(R.layout.home);

                            //borrartodo lo de la tabla usuario
                            //borrar_tabla_usuario();

                            //registrar este usuario para permitirle ingresar en local
                            RegistrarDataUser(user.getText().toString(),password.getText().toString());
                            Vendedor vendedor = new Vendedor();
                            vendedor.setVendedor(user.getText().toString());
                            //setContentView(R.layout.grafica_ventas);
                            Intent intent= new Intent (Login.this, MainActivity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(getApplicationContext(), "Datos invalidos", Toast.LENGTH_SHORT).show();
                            password.setText("");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    //loguar en local
                    Toast.makeText(Login.this,
                            "Error", Toast.LENGTH_SHORT);
                }




            }
        } catch (Exception e) {//capturamos los errores que ubieran
            Toast toast=Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_LONG);
            //mostramos el mensaje
            toast.show();
        }
    }

    private void logear(String usuariol, String clavel) {

        try {
            String sqlstatement = "select * from F_users_mobile_app where vendedor =? and clave =? and estado =? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlstatement);
            preparedStatement.setString(1, user.getText().toString());
            preparedStatement.setString(2,password.getText().toString());
            preparedStatement.setString(3,"A");
            ResultSet set = preparedStatement.executeQuery();

            if (set.next()){
                Toast.makeText(getApplicationContext(), "Felicidades. encontrado", Toast.LENGTH_SHORT).show();
                //setContentView(R.layout.home);
                RegistrarDataUser(user.getText().toString(),password.getText().toString());
                //setContentView(R.layout.grafica_ventas);
                Intent intent= new Intent (Login.this, MainActivity.class);
                startActivity(intent);

            }else{
                Toast.makeText(getApplicationContext(), "Datos invalidos", Toast.LENGTH_SHORT).show();
                password.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    //metodo de ingreso
    public boolean InicioSesion(String usuario, String contrasena){
        boolean validado = false;
        /*Creamos un objeto de la clase DBHelper e
        instanciamos el constructor y damos el nonbre de
         la base de datos y la version*/
        DBHelper admin=new DBHelper(this,nombre_DB,null,1);
        /*Abrimos la base de datos como escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        /*Creamos dos variables string y capturamos los datos
         ingresado por el usuario y lo convertimos a string*/
         usuario=user.getText().toString();
         contrasena=password.getText().toString();
        /*inicializamos al cursor y llamamos al objeto de la base
        de datos para realizar un sentencia query where donde
         pasamos las dos variables nombre de usuario y password*/
        Cursor fila = db.rawQuery("select username,clave_user from userstable where username='" +
                usuario + "' and clave_user='" + contrasena + "'", null);
        /*Realizamos un try catch para captura de errores*/
        try {
            /*Condicional if preguntamos si cursor tiene algun dato*/
            if(fila.moveToFirst()){
                //capturamos los valores del cursos y lo almacenamos en variable
                String usua=fila.getString(0);
                String pass=fila.getString(1);
                //preguntamos si los datos ingresados son iguales
                if (usuario.equals(usua)&&contrasena.equals(pass)){
                    //si son iguales entonces vamos a otra ventana
                    //Menu es una nueva actividad empty
              /////Intent ven=new Intent(this, PrincipalMenu.class);
                    //lanzamos la actividad
               ////// startActivity(ven);
                    //limpiamos las las cajas de texto
                    user.setText("ingreso local");
                    password.setText("");
                    //ACA DEBEMOS ENVIAR A LA OTRA VENTANA
                    validado =  true;
                }
            }//si la primera condicion no cumple entonces que envie un mensaje toast
            else {
                Toast toast=Toast.makeText(this,"Datos incorrectos",Toast.LENGTH_LONG);
                //mostramos el toast
                toast.show();
                validado =  false;
            }

        } catch (Exception e) {//capturamos los errores que ubieran
            Toast toast=Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_LONG);
            //mostramos el mensaje
            toast.show();
            validado =  false;
        }
        return  validado;
    }

    //metodo que nos envia a otra ventana
    public void RegistroData(View v){
        //creamos una variables e instanciamos al intent para que me muestra la clase
        /////Intent rdata=new Intent(this, RegistroData.class);
        //lanzamos la actividad
       ///// startActivity(rdata);
    }

    /*Metodo para borrar la tabla usuarios*/
    public void borrar_tabla_usuario(){
        DBHelper admin=new DBHelper(this,nombre_DB,null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        db.execSQL("  DELETE FROM  userstable  WHERE user <> 'admin'");
    }

    /*Metodo Para registrar los datos del usuario*/
    public void RegistrarDataUser(String usuario, String clave){
        /*creamos un objeto de la clase DBHelper
         * inicializamos el constructor
         * nombramos la base de datos
         * version de la base de datos*/
        DBHelper admin=new DBHelper(this,nombre_DB,null,1);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        /*creamos dos variables string
         * inicializamos y convertimos*/
        String UserName=user.getText().toString();
        String PassUser=password.getText().toString();
        /*Creamos un objeto contentvalues y instanciamos*/
        ContentValues values = new ContentValues();
        /*capturamos valores*/
        values.put("username",UserName);
        values.put("clave_user",PassUser);
        /*llamamos al insert damos el nombre de la base de datos
         * y los valores*/
        db.insert("userstable",null,values);
        /*cerramos la base de datos*/
        db.close();
        /*Lanzamos una notificacion toast*/
        Toast ToastMens= Toast.makeText(this,"Usuario registrado",Toast.LENGTH_SHORT);
        /*mostramos el toast*/
        ToastMens.show();
        /*lanzamos la actividad*/
      /////  Intent intent=new Intent(this, Login.class);
        /*iniciamos la actividad*/
    /////    startActivity(intent);
        }


    public void onClick(View view) {
            Intent intent=new Intent(this, MainActivity.class);
            /*iniciamos la actividad*/
                startActivity(intent);
    }
}











