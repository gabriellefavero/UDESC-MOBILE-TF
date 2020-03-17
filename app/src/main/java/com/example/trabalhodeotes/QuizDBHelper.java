package com.example.trabalhodeotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class QuizDBHelper extends SQLiteOpenHelper {
    private final static int DATABASE_VERSION = 5;

    private SQLiteDatabase db;

    public QuizDBHelper(Context context) {
        super(context, "NossoQuiz.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        db.execSQL("CREATE TABLE quiz_perguntas (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "pergunta TEXT, " +
            "opcao1 TEXT, " +
            "opcao2 TEXT, " +
            "opcao3 TEXT, " +
            "resposta INTEGER)"
        );
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS quiz_perguntas");
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Pergunta q1 = new Pergunta("Quem dá essa aula?", "Karina", "Kira", "Rui", 2);
        addQuestion(q1);
        Pergunta q2 = new Pergunta("O Android Studio é lerdo?", "Sim", "Nope", "É uma tartaruga", 3);
        addQuestion(q2);
        Pergunta q3 = new Pergunta("Chineque, Xineque ou Chineck?", "Chineque", "Xineque", "Chineck", 1);
        addQuestion(q3);
        Pergunta q4 = new Pergunta("É um país da europa", "Holanda", "Itália", "Espanha", 1);
        addQuestion(q4);
        Pergunta q5 = new Pergunta("Tá pegando fogo _____", "Velho", "Mano", "Bicho", 3);
        addQuestion(q5);
        Pergunta q6 = new Pergunta("Qual o nome do maior sucesso musical do Faustão?", "Rap do bolo", "Rap do ovo", "Rap do miojo", 2);
        addQuestion(q6);
        Pergunta q7 = new Pergunta("Biscoito ou bolacha?", "Biscoito", "Bolacha", "Os dois são a mesma coisa", 2);
        addQuestion(q7);
        Pergunta q8 = new Pergunta("Qual desses filmes nunca foi exibido na Sessão da Tarde?", "Se eu fosse você", "Se e fosse você 2", "Os vingadores", 3);
        addQuestion(q8);
        Pergunta q9 = new Pergunta("Qual é a jóia do infinito azul?", "Espaço", "Tempo", "Poder", 1);
        addQuestion(q9);
        Pergunta q10 = new Pergunta("Que nota esse trabalho lindo merece", "10", "0", "1", 1);
        addQuestion(q10);
    }

    private void addQuestion(Pergunta question) {
        ContentValues cv = new ContentValues();
        cv.put("pergunta", question.getPergunta());
        cv.put("opcao1", question.getOpcao1());
        cv.put("opcao2", question.getOpcao2());
        cv.put("opcao3", question.getOpcao3());
        cv.put("resposta", question.getResposta());
        db.insert("quiz_perguntas", null, cv);
    }

    public ArrayList<Pergunta> getAllQuestions() {
        ArrayList<Pergunta> questionList = new ArrayList();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM quiz_perguntas", null);

        if (c.moveToFirst()) {
            do {
                Pergunta pergunta = new Pergunta();
                pergunta.setPergunta(c.getString(c.getColumnIndex("pergunta")));
                pergunta.setOpcao1(c.getString(c.getColumnIndex("opcao1")));
                pergunta.setOpcao2(c.getString(c.getColumnIndex("opcao2")));
                pergunta.setOpcao3(c.getString(c.getColumnIndex("opcao3")));
                pergunta.setResposta(c.getInt(c.getColumnIndex("resposta")));
                questionList.add(pergunta);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
