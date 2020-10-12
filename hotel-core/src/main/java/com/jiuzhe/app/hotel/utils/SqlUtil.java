package com.jiuzhe.app.hotel.utils;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class SqlUtil {
    private JdbcTemplate jdbcTemplate;
    private String sql;
    private String action;
    private List columns;
    private List values;
    private List conditions;
    private String table;

    public SqlUtil(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.action = "";
        this.sql = "";
        this.table = "";
        this.columns = new ArrayList<String>();
        this.values = new ArrayList<String>();
        this.conditions = new ArrayList<String>();
    }

    public SqlUtil reset() {
        this.action = "";
        this.sql = "";
        this.columns.clear();
        this.values.clear();
        this.conditions.clear();
        return this;
    }

    private List<Map<String,String>> convert(List<Map<String,Object>> rawList) {
        int rawSize = rawList.size();
        if (rawSize == 0)
            return null;

        List<Map<String,String>> rs = new ArrayList<Map<String,String>>();

        Map temp = null;
        for (int i = 0; i < rawSize; i++) {
            Map<String,String> rsMap = new HashMap<String,String>();
            temp = rawList.get(i);
            temp.forEach((k,v) -> {if(v != null) {rsMap.put(k.toString(),v.toString());}});
            rs.add(rsMap);
        }

        return rs;
    }

    public SqlUtil select() {
        this.action = "select";
        return this;
    }

    public SqlUtil update() {
        this.action = "update";
        return this;
    }

    public SqlUtil delete() {
        this.action = "delete";
        return this;
    }

    public SqlUtil insert() {
        this.action = "insert";
        return this;
    }

    public String getSql() {
        return sql;
    }

    public SqlUtil column(String... c) {
        for (int i = 0; i < c.length; i++)
            columns.add(c[i]);
        return this;
    }

    public SqlUtil value(String... v) {
        for (int i = 0; i < v.length; i++)
            values.add("'" + v[i] + "'");
        return this;
    }

    public SqlUtil valueI(String v) {
        values.add(v);
        return this;
    }

    public SqlUtil condition(String condition) {
        conditions.add(condition);
        return this;
    }

    public SqlUtil conditionI(String condition, String value) {
        conditions.add(condition + value);
        return this;
    }

    public SqlUtil condition(String condition, String value) {
        conditions.add(condition + "'" + value + "'");
        return this;
    }

    public SqlUtil table(String t) {
       this.table = t;
       return this;
    }

    private String assembleCondition() {
        int csize = conditions.size();
        if (csize == 0)
            return "";

        String sql = " where ";
        for (int i = 0; i < csize; i++) {
            sql += conditions.get(i);
            if (i == conditions.size() - 1)
                break;
            sql += " and ";
        }
        return sql;
    }

    private String assembleSelect() {
        String sql = "select ";
        for (int i = 0; i < columns.size(); i++) {
            sql += columns.get(i);
            if (i == columns.size() - 1) {
                sql += " from ";
                break;
            }
            sql += " ,";
        }
        sql += table + assembleCondition();
        return sql;
    }

    private String assembleInsert() {
        int csize = columns.size();
        int vsize = values.size();
        String sql = "insert into " + table + "(";
        for (int i = 0; i < csize; i++) {
            sql += columns.get(i);
            if (i == columns.size() - 1) {
                sql += ")";
                break;
            }
            sql += ",";
        }

        int j =  vsize / csize;
        sql += " values ";
        for (int i = 0; i < j; i++) {
            sql += "(";
            for (int z = 0; z < csize; z++) {
                sql += (values.get(i * csize + z));

                if (z == csize - 1) {
                    sql += ")";
                    break;
                }
                sql += ",";
            }

            if (i == j - 1) {
                sql += ";";
                break;
            }
            sql += ",";
        }
        return sql;
    }

    private String assembleUpdate() {
        String sql = "update " + table + " set ";
        for (int i = 0; i < columns.size(); i++) {
            sql += columns.get(i) + " = " + values.get(i);
            if (i == columns.size() - 1) {
                break;
            }
            sql += ",";
        }

        sql += assembleCondition();
        return sql;
    }

    private String assembleDelete() {
        String sql = "delete from " + table + assembleCondition();
        return sql;
    }

    public List<Map<String,String>> query() {
        String sql = assembleSelect();
        return query(sql);
    }

     public List<Map<String,String>> query(String sql) {
        return convert(jdbcTemplate.queryForList(sql));
    }

    public int modify() {
        String sql = "";
        switch (action) {
            case "insert":
                sql = assembleInsert();
                break;
            case "update":
                sql = assembleUpdate();
                break;
            case "delete":
                sql = assembleDelete();
                break;
        }

        this.sql = sql;
        if (sql.equals(""))
            return -1;

        return modify(sql);
    }

    public int modify(String sql) {
        return jdbcTemplate.update(sql);
    }
    
}
