package com.example.eventsapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {Evento.class}, version = 2, exportSchema = false)
public abstract class BaseDeDatos extends RoomDatabase {

    static Executor executor = Executors.newSingleThreadExecutor();

    public abstract EventosDao obtenerEventosDao();
    //Los metodos de acceso a la base de datos
    @Dao
    public interface EventosDao {
        @Insert
        void insertarEvento(Evento evento);

        @Insert
        void insertarEvento(List<Evento> eventos);

        @Query("SELECT * FROM Evento")
        LiveData<List<Evento>> obtenerEventos();

        @Delete
        void eliminar(Evento evento);
    }

    private static volatile BaseDeDatos db;

    // Patron singleton
    public static BaseDeDatos getInstance(final Context context){
        if (db == null) {
            synchronized (BaseDeDatos.class) {
                if (db == null) {
                    db = Room.databaseBuilder(context, BaseDeDatos.class, "app.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    insertarDatosIniciales(getInstance(context).obtenerEventosDao());
                                }

                                @Override
                                public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                                    super.onDestructiveMigration(db);
                                    insertarDatosIniciales(getInstance(context).obtenerEventosDao());
                                }
                            })
                            .build();
                }
            }
        }
        return db;
    }
    private static void insertarDatosIniciales(EventosDao dao) {
        List<Evento> eventos = Arrays.asList(
                new Evento("Manolo García","10 de enero","Vive de nuevo todos los temas del artista catalán en modo acústico en su visita a Barcelona.","file:///android_asset/manolo.png"),
                new Evento("Cirque du Soleil","27 de febrero","¡Ingresa al imperio de KÀ! Una aventura épica de amor y conflicto llega a Barcelona.","file:///android_asset/circo.png"),
                new Evento("El Mago Pop","12 de marzo","Vive el nuevo espectáculo del extraordinario Mago Pop en un recinto único en Barcelona.","file:///android_asset/magopop.png"),
                new Evento("Goyo Jiménez","15 de abril","Aiguantulivinamérica 2 - Vuelve el único e inimitable experto en asuntos americanos, Goyo Jiménez.","file:///android_asset/goyo.png"),
                new Evento("Susans Red Nipples","21 de mayo","Conoce a este pintoresco grupo rock-punk en un concierto único.","file:///android_asset/susans.png"),
                new Evento("J Balvin","9 de junio","J Balvin aterriza en Barcelona presentando Colores, su nuevo álbum.","file:///android_asset/jbalvin.png"),
                new Evento("Unite with Tomorrowland","29 de julio","Vive la experiencia Tomorrowland al ritmo de tus djs preferidos y tops mundiales.","file:///android_asset/tomorrowland.png"),
                new Evento("El Rey León","5 de agosto","Descubre todo sobre el musical que ya han disfrutado cerca de 5.000.000 espectadores.","file:///android_asset/reyleon.png"),
                new Evento("Love the 90's","3 de septiembre","El mayor festival de los 90 con tres escenarios con lo mejor del dance, del pop/rock y los hits del verano.","file:///android_asset/love90.png"),
                new Evento("El Tricicle","21 de octubre","Lo mejor de lo mejor de Tricicle, o casi, durante tiempo limitado.","file:///android_asset/tricicle.png"),
                new Evento("Martita de Graná","29 de noviembre","La humorista revoluciona el mundo de la comedia con su desparpajo y naturalidad.","file:///android_asset/martita.png"),
                new Evento("Fito & Fitipaldis","10 de diciembre","Presentando el último disco llegan a Barcelona para hacer cantar y tararear sus temas a todo el mundo.","file:///android_asset/fito.png")
        );
        executor.execute(()-> dao.insertarEvento(eventos));
    }
}
