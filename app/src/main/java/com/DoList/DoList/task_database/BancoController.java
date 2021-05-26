package com.DoList.DoList.task_database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class BancoController extends SQLiteAssetHelper {

    public static final int BANK_VERSION = 1;
    public static final String BANK_NAME = "tasks.sqlite";

    public BancoController(Context context) {
        super(context, BANK_NAME, null, BANK_VERSION);
    }
}