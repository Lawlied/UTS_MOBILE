package uts.taufikhidayat.task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AgendaDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agenda.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_AGENDA = "agenda";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_STATUS = "status";

    private static AgendaDatabaseHelper instance;

    private AgendaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Singleton untuk menghindari lebih dari satu instance
    public static synchronized AgendaDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new AgendaDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_AGENDA_TABLE = "CREATE TABLE " + TABLE_AGENDA + "("
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_STATUS + " TEXT)";
        db.execSQL(CREATE_AGENDA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENDA);
        onCreate(db);
    }

    public boolean insertAgenda(String name, String description, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_STATUS, status);

        long result = db.insert(TABLE_AGENDA, null, values);
        db.close();
        return result != -1; // Return true jika insert berhasil
    }

    // Method to get all agendas from the database
    public List<Agenda> getAllAgenda() {
        List<Agenda> agendaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_AGENDA,
                new String[]{COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_STATUS},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS));

                Agenda agenda = new Agenda(name, description, status);
                agendaList.add(agenda);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return agendaList;
    }
}
