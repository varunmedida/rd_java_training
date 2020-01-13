package com.epam.engx.cleancode.complextask.task1;



import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.Command;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DataSet;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.View;
import com.epam.engx.cleancode.complextask.task1.thirdpartyjar.DatabaseManager;

import java.util.List;


public class Print implements Command {

    private View view;
    private DatabaseManager manager;
    private String tableName;

    public Print(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    public boolean canProcess(String command) {
        return command.startsWith("print ");
    }

    public void process(String input) {
        String[] command = input.split(" ");
        if (command.length != 2) {
            throw new IllegalArgumentException("incorrect number of parameters. Expected 1, but is " + (command.length - 1));
        }
        tableName = command[1];
        List<DataSet> data = manager.getTableData(tableName);
        view.write(getTableString(data));
    }

    private String getTableString(List<DataSet> data) {
        int maxColumnSize;
        maxColumnSize = getMaxColumnSize(data);
        if (maxColumnSize == 0) {
            return getEmptyTable(tableName);
        } else {
            return getHeaderOfTheTable(data) + getStringTableData(data);
        }
    }

    private String getEmptyTable(String tableName) {
        String textEmptyTable = "║ Table '" + tableName + "' is empty or does not exist ║";
        String result = "╔";
        for (int i = 0; i < textEmptyTable.length() - 2; i++) {
            result += "═";
        }
        result += "╗\n";
        result += textEmptyTable + "\n";
        result += "╚";
        for (int i = 0; i < textEmptyTable.length() - 2; i++) {
            result += "═";
        }
        result += "╝\n";
        return result;
    }
    private int checkMax(int maxLength,DataSet dataSet)
    {
        List<Object> values = dataSet.getValues();
        for (Object value : values) {
                if (String.valueOf(value).length() > maxLength) {
                    maxLength = String.valueOf(value).length();
                }
    }
    return maxLength;
    }
    private int getMaxColumnSize(List<DataSet> dataSets) {
        int maxLength = 0;
        if (dataSets.size() > 0) {
            for (DataSet dataSet : dataSets) {
                 maxLength=checkMax(maxLength, dataSet);
                }
            }
        
        return maxLength;
    }


    private String getStringTableData(List<DataSet> dataSets) {
        int rowsCount;
        rowsCount = dataSets.size();
        int maxColumnSize = getMaxColumnSize(dataSets);
        String result = "";
        if (maxColumnSize % 2 == 0) {
            maxColumnSize += 2;
        } else {
            maxColumnSize += 3;
        }
        int columnCount = getColumnCount(dataSets);
        for (int row = 0; row < rowsCount; row++) {
            result = result+resultOnColoumnNames(dataSets,maxColumnSize);
            result += "\n";
            if (row < rowsCount - 1) {
                result=result+getOpeners(columnCount, maxColumnSize);
            }
        }
        result=result+getFinisher(columnCount, maxColumnSize);
        return result;
    }

    private int getColumnCount(List<DataSet> dataSets) {
        int result = 0;
        if (dataSets.size() > 0) {
            return dataSets.get(0).getColumnNames().size();
        }
        return result;
    }
    private String ItrerateThroughColumns(int maxColumnSize,int columnNamesLength)
    {
        String result="";
        for (int j = 0; j < (maxColumnSize - columnNamesLength) / 2; j++) {
            result += " ";
        }
        return result;
    }
    
    private String resultOnColoumnNames(List<DataSet> dataSets,int maxColumnSize)
    {
        String result="";
        int columnCount = getColumnCount(dataSets);
        List<String> columnNames = dataSets.get(0).getColumnNames();
        for (int column = 0; column < columnCount; column++) {
            result += "║";
            int columnNamesLength = columnNames.get(column).length();
            result=result+ItrerateThroughColumns(maxColumnSize, columnNamesLength);
        }
        return result;
    }
    public String addingSymbolEqualTo(int maxColumnSize)
    {
        String result="";
        for (int i = 0; i < maxColumnSize; i++) {
            result += "═";
        }
        return result;

    }
    private String getOpeners(int columnCount,int maxColumnSize)
    {
        String result="";
        
            result += "╠";
            for (int j = 1; j < columnCount; j++) {
                result=result+addingSymbolEqualTo(maxColumnSize);
                result=result+"╬";
                result=result+addingSymbolEqualTo(maxColumnSize);
            }
            result += "╣\n";
            return result;
        }

    public String getFinisher(int columnCount,int maxColumnSize) {
        String result="";
        result += "╚";
        for (int j = 1; j < columnCount; j++) {
            result=result+addingSymbolEqualTo(maxColumnSize);
            result += "╩";
            result=result+addingSymbolEqualTo(maxColumnSize);
            }
            result += "╝\n";
            return result;
        }
    
    private String getHeaderOfTheTable(List<DataSet> dataSets) {
        int maxColumnSize = getMaxColumnSize(dataSets);
        String result = "";
        int columnCount = getColumnCount(dataSets);
        if (maxColumnSize % 2 == 0) {
            maxColumnSize += 2;
        } else {
            maxColumnSize += 3;
        }
        result += "╔";
        for (int j = 1; j < columnCount; j++) {
            result=result+addingSymbolEqualTo(maxColumnSize);
            result += "╦";
        }
        result=result+addingSymbolEqualTo(maxColumnSize);
        result += "╗\n";
        result = result+resultOnColoumnNames(dataSets,maxColumnSize); // Adding result to Coloumn Names.
        result += "║\n";

        //last string of the header
        if (dataSets.size() > 0) {
           result=result+getOpeners(columnCount, maxColumnSize);
        } else {
            result=result+getFinisher(columnCount, maxColumnSize);
        }
        return result;
    }
}