package com.example.navigationdrawerpractica.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    /*Este metodo nos ayuda a crear la base de datos que un no existe*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Creacion de la tabla con 3 campos un integer que es auto incrementable
         * un texto que no puede ser nulo que es el nombre del usuario
         * la contraseña que tambien es un texto y no nulo*/
        db.execSQL("create table userstable(id_user integer  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "username text NOT NULL,clave_user text NOT NULL)");
        /*Hacemos un insert para tener un valkor insertado como predeterminado*/
        db.execSQL("insert into userstable(username,clave_user) values('admin','admin')");

        /*Creacion de la tabla clientes para cada vendedor*/
        db.execSQL("create table userclientes(id_user integer  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nit text NOT NULL," +
                "nombres text NOT NULL," +
                "direccion text NOT NULL," +
                "telefonos text  ," +
                "email text  ," +
                "estado text NOT NULL," +
                "cupo_credito text NOT NULL," +
                "lista_precios text NOT NULL," +
                "descuentos text NOT NULL," +
                "usado text ," +
                "condicion text NOT NULL," +
                "lista_precios2 text ," +
                "notas text  )");

        /*Creacion de la tabla presupuesto para cada vendedor*/
        db.execSQL("create table presupuestoventas(vendedor text NOT NULL ," +
                "presupuesto integer NOT NULL ," +
                "dias_habiles integer not null)");

        /*Creacion de la tabla ventas dia para cada vendedor*/
        db.execSQL("create table ventasvendedor(vendedor text  NOT NULL," +
                "fecha text ," +
                "ventas integer NOT NULL)");

        /*Creacion de la tabla ventas para cada vendedor*/
        db.execSQL("create table ventastot(vendedor text  NOT NULL," +
                "ventas text NOT NULL)");

        /*Creacion de la tabla detallecartera para cada vendedor*/
        db.execSQL("create table detallecartera(nit text  NOT NULL," +
                "nombres text NOT NULL," +
                "direccion text ," +
                "telefonos text  ," +
                "tipo text  ," +
                "numero text NOT NULL," +
                "fecha text NOT NULL," +
                "vencimiento text NOT NULL," +
                "saldo text NOT NULL," +
                "valor_total text  )");

        /*Creacion de la tabla cartera para cada vendedor*/
        db.execSQL("create table cartera(nit text  NOT NULL," +
                "nombres text NOT NULL," +
                "direccion text ," +
                "telefonos text  ," +
                "dias text  ," +
                "valor_total text  )");

        /*Creacion de la tabla lista precios esta es general*/
        db.execSQL("create table lista_precios(grupo text  NOT NULL," +
                "subgrupo text NOT NULL," +
                "codigo text NOT NULL," +
                "descripcion text NOT NULL ," +
                "distribuidor text  ," +
                "mayorista text  ," +
                "detallista text  )");

        /*Creacion de la tabla lista precios esta es general*/
        db.execSQL("create table ventas_detalle(grupo text  NOT NULL," +
                "subgrupo text NOT NULL," +
                "kilos_ppto integer NOT NULL," +
                "valor_ppto integer NOT NULL," +
                "kilos_vta integer NOT NULL," +
                "valor_vta integer NOT NULL," +
                "presupuestado text )");

        /*Creacion de la tabla presupuesto de recaudo para cada vendedor*/
        db.execSQL("create table presupueto_recaudo(presupuesto integer NOT NULL)");

        /*Creacion de la tabla recaudo de recaudo para cada vendedor*/
        db.execSQL("create table recaudo_vendedor(recaudo integer NOT NULL)");
    }



    /*Este metodo nos ayuda a administrar la versiones de la base de datos creada*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        /*Creacion de la tabla con 3 campos un integer que es auto incrementable
         * un texto que no puede ser nulo que es el nombre del usuario
         * la contraseña que tambien es un texto y no nulo*/
        db.execSQL("create table userstable(id_user integer  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "username text NOT NULL,clave_user text NOT NULL)");
        /*Creacion de la tabla clientes para cada vendedor*/
        db.execSQL("create table userclientes(id_user integer  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nit text NOT NULL," +
                "nombres text NOT NULL," +
                "direccion text NOT NULL," +
                "telefonos text  ," +
                "email text  ," +
                "estado text NOT NULL," +
                "cupo_credito text NOT NULL," +
                "lista_precios text NOT NULL," +
                "descuentos text NOT NULL," +
                "usado text ," +
                "condicion text NOT NULL," +
                "lista_precios2 text ," +
                "notas text  )");

        /*Hacemos un insert para tener un valkor insertado como predeterminado*/
        db.execSQL("insert into userstable(username,clave_user) values('admin','admin')");


    }}