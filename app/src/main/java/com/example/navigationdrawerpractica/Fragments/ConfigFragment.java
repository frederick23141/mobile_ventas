package com.example.navigationdrawerpractica.Fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.navigationdrawerpractica.ConSQL;
import com.example.navigationdrawerpractica.DataBase.DBHelper;
import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.Entidades.Vendedor;
import com.example.navigationdrawerpractica.Interfaces.iComunicaFragments;
import com.example.navigationdrawerpractica.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ConfigFragment extends Fragment {

    Switch swuser;
    Switch swclient;
    Switch swrut;
    Switch swmaestroinventrari;
    Switch swcarter;
    Switch swdetallecarter;
    Switch swstoc;
    Switch swpresupuest;
    Switch swsendclient;
    Switch swsendrut;
    Switch swsendpedid;
    Switch swventa;
    Switch swlistaprecio;
    Button procesar;

    Vendedor vendedor;

    String nombre_DB = "DB_MOBILE";
    String vend;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.config_fragment,container,false);

        swuser = (Switch) view.findViewById(R.id.swusuarios);
        swclient = (Switch) view.findViewById(R.id.swclientes);
        swrut = (Switch) view.findViewById(R.id.swrutas);
        swmaestroinventrari = (Switch) view.findViewById(R.id.swmaestroinventario);
        swcarter = (Switch) view.findViewById(R.id.swcartera);
        swdetallecarter = (Switch) view.findViewById(R.id.swdetallecartera);
        swstoc = (Switch) view.findViewById(R.id.swstock);
        swpresupuest = (Switch) view.findViewById(R.id.swpresupuesto);
        swsendclient = (Switch) view.findViewById(R.id.swclienteenviar);
        swsendrut = (Switch) view.findViewById(R.id.swrutasenviar);
        swsendpedid = (Switch) view.findViewById(R.id.swpedidosenviar);
        swventa = (Switch) view.findViewById(R.id.swventas);
        swlistaprecio = (Switch) view.findViewById(R.id.swlistaprecios);

        procesar = (Button) view.findViewById(R.id.btnprocesar);
        vendedor = new Vendedor();
        ConSQL c = new ConSQL();
        Connection connection = c.conclass();
        vend = vendedor.getVendedor();

        procesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                procesar_sincronizacion();

            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    public void procesar_sincronizacion(){
        if(swuser.isChecked()){
            Toast.makeText(this.getContext(), "Sincronizar usuario", Toast.LENGTH_SHORT).show();
        }

        //cargar lista de precios
        if(swlistaprecio.isChecked()){
            Toast.makeText(this.getContext(), "Sincronizar lista precios", Toast.LENGTH_SHORT).show();
            //CREAMOS LA CONEXION A LA BASE DE DATOS REAL, BORRAMOS LA TABLA DE USUARIOS Y AGREGAMOS ESTE NUEVO USUARIO
            ConSQL c = new ConSQL();
            Connection connection = c.conclass();
            if(c != null){
                //logear en real
                try {
                    DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
                    /*Abrimos la base de datos para escritura*/
                    SQLiteDatabase db=admin.getWritableDatabase();
                    /*creamos dos variables string
                     * inicializamos y convertimos*/
                    db.execSQL("delete from lista_precios");
                    String grupo;
                    String subgrupo;
                    String codigo;
                    String descripcion;
                    String distribuidor;
                    String mayorista;
                    String detallista;

                    String sqlstatement = "SELECT Nom_grupo,Nom_sub_grupo,codigo,descripcion,Distribuidor,Mayorista,Detallista FROM bi_lista_precios";

                    PreparedStatement preparedStatement = connection.prepareStatement(sqlstatement);
                    ResultSet set = preparedStatement.executeQuery();

                    while (set.next()){
                        Toast.makeText(this.getContext(), "insertando", Toast.LENGTH_SHORT).show();
                        /*Creamos un objeto contentvalues y instanciamos*/
                        ContentValues values = new ContentValues();
                        /*capturamos valores*/
                        values.put("grupo",set.getString("Nom_grupo"));
                        values.put("subgrupo",set.getString("Nom_sub_grupo"));
                        values.put("codigo",set.getString("codigo"));
                        values.put("descripcion",set.getString("descripcion"));
                        values.put("distribuidor",set.getString("Distribuidor"));
                        values.put("mayorista",set.getString("Mayorista"));
                        values.put("detallista",set.getString("Detallista"));
                        /*llamamos al insert damos el nombre de la base de datos
                         * y los valores*/
                        db.insert("lista_precios",null,values);
                        Toast.makeText(this.getContext(), "sincronizado lista precios con ", Toast.LENGTH_SHORT).show();
                    }
                    /*cerramos la base de datos*/
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                //loguar en local
                Toast.makeText(this.getContext(), "Error", Toast.LENGTH_SHORT);
            }
        }

        if(swclient.isChecked()){
            Toast.makeText(this.getContext(), "Sincronizar cliente", Toast.LENGTH_SHORT).show();

                //CREAMOS LA CONEXION A LA BASE DE DATOS REAL, BORRAMOS LA TABLA DE USUARIOS Y AGREGAMOS ESTE NUEVO USUARIO

                ConSQL c = new ConSQL();
                Connection connection = c.conclass();
                vend = vendedor.getVendedor();

                if(c != null){
                    //logear en real
                    try {
                        DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
                        /*Abrimos la base de datos para escritura*/
                        SQLiteDatabase db=admin.getWritableDatabase();
                        /*creamos dos variables string
                         * inicializamos y convertimos*/

                        db.execSQL("delete from userclientes");
                        String nit;
                        String nombres;
                        String direccion;
                        String celular;
                        String mail;
                        String bloqueo;
                        String cupo_credito;
                        String lista;
                        String descuento_fijo;
                        String condicion;
                        String notas;

                        String sqlstatement = "SELECT   p.nit, CONCAT(p.nombres, ' - ', p.razon_comercial) as nombres, p.direccion, p.celular, p.mail, p.bloqueo, p.cupo_credito, p.lista, t.descuento_fijo, t.condicion, t.notas\n" +
                                "FROM            dbo.PI_clientes AS p INNER JOIN\n" +
                                "                         dbo.terceros AS t ON p.nit = t.nit\n" +
                                "WHERE        p.vendedor =  ? ORDER BY p.nombres " ;

                        PreparedStatement preparedStatement = connection.prepareStatement(sqlstatement);
                        preparedStatement.setString(1, vend);
                        ResultSet set = preparedStatement.executeQuery();

                        while (set.next()){
                            Toast.makeText(this.getContext(), "insertando", Toast.LENGTH_SHORT).show();
                            /*Creamos un objeto contentvalues y instanciamos*/
                            ContentValues values = new ContentValues();
                            /*capturamos valores*/
                            values.put("nit",set.getString(1));
                            values.put("nombres",set.getString(2));
                            values.put("direccion",set.getString(3));
                            values.put("telefonos",set.getString(4));
                            values.put("email",set.getString(5));
                            values.put("estado",set.getString(6));
                            values.put("cupo_credito",set.getString(7));
                            values.put("lista_precios",set.getString(8));
                            values.put("descuentos",set.getString(9));
                            values.put("usado","0");
                            values.put("condicion",set.getString(10));
                            values.put("lista_precios2","0");
                            values.put("notas",set.getString(11));
                            /*llamamos al insert damos el nombre de la base de datos
                             * y los valores*/
                            db.insert("userclientes",null,values);
                        }
                        /*cerramos la base de datos*/
                        db.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    //loguar en local
                    Toast.makeText(this.getContext(), "Error", Toast.LENGTH_SHORT);
                }
        }

        //**** CARGAR EL PRESUPUESTO   ****//
        if(swpresupuest.isChecked()){
            Toast.makeText(this.getContext(), "Sincronizar usuario", Toast.LENGTH_SHORT).show();
            //CREAMOS LA CONEXION A LA BASE DE DATOS REAL, BORRAMOS LA TABLA DE USUARIOS Y AGREGAMOS ESTE NUEVO USUARIO
            ConSQL c = new ConSQL();
            Connection connection = c.conclass();
            vend = vendedor.getVendedor();

            if(c != null){
                //logear en real
                try {
                    DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
                    /*Abrimos la base de datos para escritura*/
                    SQLiteDatabase db=admin.getWritableDatabase();
                    /*creamos dos variables string
                     * inicializamos y convertimos*/
                    db.execSQL("delete from presupuestoventas");
                    String vendedor;
                    String presupuesto;

                    String sqlstatement = "SELECT        CONVERT(int, ROUND(SUM(valor), 0)) AS valor,diasHabiles\n" +
                            "FROM            dbo.presupuesto_ventas\n" +
                            "WHERE        (vendedor = ? ) AND (ano = YEAR(GETDATE())) AND (mes = MONTH(GETDATE())) AND (id_definicion = 3) group by diasHabiles" ;

                    PreparedStatement preparedStatement = connection.prepareStatement(sqlstatement);
                    preparedStatement.setString(1, vend);
                    ResultSet set = preparedStatement.executeQuery();

                    while (set.next()){
                        Toast.makeText(this.getContext(), "insertando", Toast.LENGTH_SHORT).show();
                        /*Creamos un objeto contentvalues y instanciamos*/
                        ContentValues values = new ContentValues();
                        /*capturamos valores*/
                        values.put("vendedor",vend);
                        values.put("presupuesto",set.getInt(1));
                        values.put("dias_habiles",set.getInt(2));
                        /*llamamos al insert damos el nombre de la base de datos
                         * y los valores*/
                        db.insert("presupuestoventas",null,values);
                    }

                    /* consultamos el  recaudo */
                    sqlstatement = "SELECT vendedor ,SUM (Total_rec) - sum(descuento) \n" +
                            "                    FROM Jjv_Recaudos_consol \n" +
                            "                        WHERE  YEAR(fecha) = YEAR(GETDATE()) and MONTH(fecha) = MONTH(GETDATE()) AND vendedor = ? \n" +
                            "                     GROUP BY vendedor";
                     preparedStatement = connection.prepareStatement(sqlstatement);
                    preparedStatement.setString(1, vend);
                     set = preparedStatement.executeQuery();
                    while (set.next()){
                        Toast.makeText(this.getContext(), "insertando", Toast.LENGTH_SHORT).show();
                        /*Creamos un objeto contentvalues y instanciamos*/
                        ContentValues values = new ContentValues();
                        /*capturamos valores*/
                        values.put("recaudo",set.getInt(2));
                        /*llamamos al insert damos el nombre de la base de datos
                         * y los valores*/
                        db.insert("recaudo_vendedor",null,values);
                    }

                    /* consultamos el presupuesto recaudo */
                    sqlstatement = " SELECT Ppto_total\n" +
                            "FROM Jjv_ppto_vtas_recaudos_consol \n" +
                            "WHERE mes =MONTH(GETDATE()) And año = YEAR(GETDATE()) and Nit = ?\n";
                    preparedStatement = connection.prepareStatement(sqlstatement);
                    preparedStatement.setString(1, vend);
                    set = preparedStatement.executeQuery();
                    while (set.next()){
                        Toast.makeText(this.getContext(), "insertando", Toast.LENGTH_SHORT).show();
                        /*Creamos un objeto contentvalues y instanciamos*/
                        ContentValues values = new ContentValues();
                        /*capturamos valores*/
                        values.put("presupuesto",set.getInt(1));
                        /*llamamos al insert damos el nombre de la base de datos
                         * y los valores*/
                        db.insert("presupueto_recaudo",null,values);
                    }

                    /*cerramos la base de datos*/
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                //loguar en local
                Toast.makeText(this.getContext(), "Error", Toast.LENGTH_SHORT);
            }
        }

        //CARGAR LA CARTERA
        if(swcarter.isChecked()){
            Toast.makeText(this.getContext(), "Sincronizar CARTERA", Toast.LENGTH_SHORT).show();
            //CREAMOS LA CONEXION A LA BASE DE DATOS REAL, BORRAMOS LA TABLA DE USUARIOS Y AGREGAMOS ESTE NUEVO USUARIO
            ConSQL c = new ConSQL();
            Connection connection = c.conclass();
            vend = vendedor.getVendedor();

            if(c != null){
                //logear en real
                try {
                    DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
                    /*Abrimos la base de datos para escritura*/
                    SQLiteDatabase db=admin.getWritableDatabase();
                    /*creamos dos variables string
                     * inicializamos y convertimos*/
                    db.execSQL("delete from cartera");
                    String vendedor;
                    String presupuesto;

                    String sqlstatement = "SELECT        c.nit,  t.nombres, t.direccion, t.telefono_1 AS telefono,\n" +
                            "                             (SELECT        TOP (1) DATEDIFF(DAY, vencimiento, GETDATE()) AS d\n" +
                            "                               FROM            dbo.PI_cartera AS c\n" +
                            "                               WHERE        (vendedor = ?) and c.nit = t.nit) AS dias, SUM(c.Saldo) AS total\n" +
                            "FROM            dbo.PI_cartera AS c INNER JOIN\n" +
                            "                         dbo.terceros AS t ON c.nit = t.nit\n" +
                            "WHERE        (c.vendedor = ? ) \n" +
                            "GROUP BY c.nit, t.nombres, t.direccion, t.telefono_1,t.nit\n" +
                            "order by t.nombres" ;

                    PreparedStatement preparedStatement = connection.prepareStatement(sqlstatement);
                    preparedStatement.setString(1, vend);
                    preparedStatement.setString(2, vend);

                    ResultSet set = preparedStatement.executeQuery();

                    while (set.next()){
                        Toast.makeText(this.getContext(), "insertando", Toast.LENGTH_SHORT).show();
                        /*Creamos un objeto contentvalues y instanciamos*/
                        ContentValues values = new ContentValues();
                        /*capturamos valores*/
                        values.put("nit",set.getString(1));
                        values.put("nombres",set.getString(2));
                        values.put("direccion",set.getString(3));
                        values.put("telefonos",set.getString(4));
                        values.put("dias",set.getString(5));
                        values.put("valor_total",set.getString(6));
                        /*llamamos al insert damos el nombre de la base de datos
                         * y los valores*/
                        db.insert("cartera",null,values);
                    }
                    /*cerramos la base de datos*/
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                //loguar en local
                Toast.makeText(this.getContext(), "Error", Toast.LENGTH_SHORT);
            }
        }

        //********* CARGAR DETALLE  CARTERA ***//
        if(swdetallecarter.isChecked()){
            Toast.makeText(this.getContext(), "Sincronizar detalle CARTERA", Toast.LENGTH_SHORT).show();
            //CREAMOS LA CONEXION A LA BASE DE DATOS REAL, BORRAMOS LA TABLA DE USUARIOS Y AGREGAMOS ESTE NUEVO USUARIO
            ConSQL c = new ConSQL();
            Connection connection = c.conclass();
            vend = vendedor.getVendedor();

            if(c != null){
                //logear en real
                try {
                    DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
                    /*Abrimos la base de datos para escritura*/
                    SQLiteDatabase db=admin.getWritableDatabase();

                    db.execSQL("delete from detallecartera");
                    /*creamos dos variables string
                     * inicializamos y convertimos*/
                    String vendedor;
                    String presupuesto;

                    String sqlstatement = "SELECT        nit, nombres, direccion, telefono_1, tipo, numero, fecha, vencimiento, Saldo, valor_total, vendedor\n" +
                            "FROM            dbo.F_V_cartera_mobile\n" +
                            "WHERE        (vendedor = ?)" ;

                    PreparedStatement preparedStatement = connection.prepareStatement(sqlstatement);
                    preparedStatement.setString(1, vend);

                    ResultSet set = preparedStatement.executeQuery();

                    while (set.next()){
                        Toast.makeText(this.getContext(), "insertando", Toast.LENGTH_SHORT).show();
                        /*Creamos un objeto contentvalues y instanciamos*/
                        ContentValues values = new ContentValues();
                        /*capturamos valores*/
                        values.put("nit",set.getString(1));
                        values.put("nombres",set.getString(2));
                        values.put("direccion",set.getString(3));
                        values.put("telefonos",set.getString(4));
                        values.put("tipo",set.getString(5));
                        values.put("numero",set.getString(6));
                        values.put("fecha",set.getString(7));
                        values.put("vencimiento",set.getString(8));
                        values.put("saldo",set.getString(9));
                        values.put("valor_total",set.getString(10));
                        /*llamamos al insert damos el nombre de la base de datos
                         * y los valores*/
                        db.insert("detallecartera",null,values);
                    }
                    /*cerramos la base de datos*/
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                //loguar en local
                Toast.makeText(this.getContext(), "Error", Toast.LENGTH_SHORT);
            }
        }

        //**** CARGAR la venta    ****//
        if(swventa.isChecked()){
            Toast.makeText(this.getContext(), "Sincronizar venta", Toast.LENGTH_SHORT).show();
            //CREAMOS LA CONEXION A LA BASE DE DATOS REAL, BORRAMOS LA TABLA DE USUARIOS Y AGREGAMOS ESTE NUEVO USUARIO
            ConSQL c = new ConSQL();
            Connection connection = c.conclass();
            vend = vendedor.getVendedor();

            if(c != null){
                //logear en real
                try {
                    DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
                    /*Abrimos la base de datos para escritura*/
                    SQLiteDatabase db=admin.getWritableDatabase();
                    /*Borramos la informacion actual*/
                    db.execSQL("delete from ventasvendedor");
                    db.execSQL("delete from ventastot");
                    db.execSQL("delete from ventas_detalle");

                    /*creamos dos variables string
                     * inicializamos y convertimos*/
                    String vendedor;
                    String presupuesto;

                    String sqlstatement = "SELECT  CONVERT(varchar,fec,111), SUM(Vr_total) AS vta_dia\n" +
                            "FROM            dbo.Bi_Auditoria_vtas3\n" +
                            "WHERE        (vendedor = ? ) AND (Mes = MONTH(GETDATE())) AND (Año = YEAR(GETDATE()))\n" +
                            "GROUP BY fec\n" +
                            "ORDER BY fec" ;

                    PreparedStatement preparedStatement = connection.prepareStatement(sqlstatement);
                    preparedStatement.setString(1, vend);
                    ResultSet set = preparedStatement.executeQuery();

                    String fechab;

                    while (set.next()){
                        Toast.makeText(this.getContext(), "insertando", Toast.LENGTH_SHORT).show();
                        /*Creamos un objeto contentvalues y instanciamos*/
                        ContentValues values = new ContentValues();
                        /*capturamos valores*/
                        values.put("vendedor",vend.toString());
                        values.put("fecha", set.getString(1));
                        values.put("ventas",set.getInt(2));
//                        values.put("fecha",set.getString(1));
//                        values.put("ventas",set.getInt(2));

                        /*llamamos al insert damos el nombre de la base de datos
                         * y los valores*/
                        db.insert("ventasvendedor",null,values);
                    }

                    cargarsumaventa();

                    /*cerramos la base de datos*/
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                //loguar en local
                Toast.makeText(this.getContext(), "Error", Toast.LENGTH_SHORT);
            }
        }


    }
    public void cargarsumaventa(){
        Toast.makeText(this.getContext(), "Sincronizar venta", Toast.LENGTH_SHORT).show();
        //CREAMOS LA CONEXION A LA BASE DE DATOS REAL, BORRAMOS LA TABLA DE USUARIOS Y AGREGAMOS ESTE NUEVO USUARIO
        ConSQL c = new ConSQL();
        Connection connection = c.conclass();
        vend = vendedor.getVendedor();

        if(c != null){
            //logear en real
            try {
                DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
                /*Abrimos la base de datos para escritura*/
                SQLiteDatabase db=admin.getWritableDatabase();
                /*Borramos la informacion actual*/
                db.execSQL("delete from ventastot");
                db.execSQL("delete from ventas_detalle");

                String sqlstatement = "select   nom_grupo,nom_subgrupo,sum(kilosppto)as kilosppto ,sum(vrppto) as vrppto ,sum (kgs_vtas) as kgs_vtas , sum(vr_vtas) as vr_vtas,CASE\n" +
                        "           WHEN sum(kilosppto) = 0 THEN 'No presupuestado'\n" +
                        "           WHEN sum(kilosppto) <> 0 THEN 'Presupuestado'\n" +
                        "       END AS vta_presupuestada\n" +
                        "from bi_presupuesto_y_ventas_vendedor\n" +
                        "where vendedor = ? and ano = YEAR(GETDATE()) and mes = MONTH(GETDATE()) and ( kilosppto <> 0 or kgs_vtas <> 0)\n" +
                        "group by nom_grupo,nom_subgrupo\n" +
                        "order by vta_presupuestada" ;

                PreparedStatement preparedStatement = connection.prepareStatement(sqlstatement);
                preparedStatement.setString(1, vend);
                ResultSet set = preparedStatement.executeQuery();

                while (set.next()){
                    Toast.makeText(this.getContext(), "insertando suma ventas", Toast.LENGTH_LONG).show();
                    /*Creamos un objeto contentvalues y instanciamos*/
                    ContentValues values = new ContentValues();
                    /*capturamos valores*/
//                    values.put("vendedor",vend.toString());
//                    values.put("ventas",set.getDouble(1));
                    values.put("grupo",set.getString(1));
                    values.put("subgrupo",set.getString(2));
                    values.put("kilos_ppto",set.getInt(3));
                    values.put("valor_ppto",set.getInt(4));
                    values.put("kilos_vta",set.getInt(5));
                    values.put("valor_vta",set.getDouble(6));
                    values.put("presupuestado",set.getString(7));

                    /*llamamos al insert damos el nombre de la base de datos
                     * y los valores*/
//                    db.insert("ventastot",null,values);
                    db.insert("ventas_detalle",null,values);
                }

                /*cerramos la base de datos*/
                db.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            //loguar en local
            Toast.makeText(this.getContext(), "Error", Toast.LENGTH_SHORT);
        }
    }


}
