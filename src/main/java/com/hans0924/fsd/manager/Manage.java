package com.hans0924.fsd.manager;

import com.hans0924.fsd.constants.ManageVarType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author 曾韩铄
 * @since 2020-03-03
 */
public class Manage {

    public static Manage manager;

    private int nVars;

    private List<ManageVar> variables;

    public Manage() {
        nVars = 0;
        variables = new ArrayList<ManageVar>();
    }

    public int addVar(String name, int type) {
        ManageVar var = new ManageVar();
        var.setType(type);
        var.setName(name);
        variables.add(var);
        return variables.size();
    }

    public void delVar(int num) {
        if (variables.size() > num) {
            variables.remove(num);
        }
    }

    public void incVar(int num) {
        ManageVar var = variables.get(num);
        ManageVarValue value = var.getValue();
        value.setNumber(value.getNumber() + 1);
    }

    public void decVar(int num) {
        ManageVar var = variables.get(num);
        ManageVarValue value = var.getValue();
        value.setNumber(value.getNumber() - 1);
    }

    public void setVar(int num, int number) {
        ManageVar var = variables.get(num);
        ManageVarValue value = var.getValue();
        value.setNumber(number);
    }

    public void setVar(int num, String str) {
        ManageVar var = variables.get(num);
        ManageVarValue value = var.getValue();
        value.setString(str);
    }

    public void setVar(int num, long timeVal) {
        ManageVar var = variables.get(num);
        ManageVarValue value = var.getValue();
        value.setTimeVal(timeVal);
    }

    public ManageVarValue getVar(int num) {
        if (num >= nVars) {
            return null;
        }
        ManageVar var = variables.get(num);
        return var.getValue();
    }

    public int getNVars() {
        return nVars;
    }

    public String sprintValue(int num) {
        if (num >= nVars || variables.get(num).getName() == null) {
            return null;
        }
        ManageVar var = variables.get(num);

        switch (var.getType()) {
            case ManageVarType
                    .ATT_INT:
                return String.valueOf(var.getValue().getNumber());
            case ManageVarType.ATT_VARCHAR:
                return var.getValue().getString();
            case ManageVarType.ATT_DATE:
                return new Date(var.getValue().getTimeVal()).toString();
        }
        return "";
    }

    public int getVarNum(String name) {
        for (int i = 0; i < variables.size(); i++) {
            ManageVar variable = variables.get(i);
            if (Objects.equals(variable.getName(), name)) {
                return i;
            }
        }

        return -1;
    }
}
