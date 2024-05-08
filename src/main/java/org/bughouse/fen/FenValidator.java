package org.bughouse.fen;

import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class FenValidator {

    private Invocable invocable;

    private static FenValidator instance;

    public static FenValidator getInstance() {
        if(instance == null) {
            instance = new FenValidator();
            instance.init();
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().validate(args[0]));
    }

    private void init() {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("ECMAScript");
        InputStream inputStream = this.getClass().getResourceAsStream("/static/js/chess.js");

        if (inputStream == null) {
            throw new RuntimeException("/static/js/chess.js not found");
        }

        try {
            engine.eval(new InputStreamReader(inputStream));
            invocable = (Invocable) engine;
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }


    public ReturnCode validate(String fen) {
        if(fen!=null) {
            fen = fen.trim();
        }

        ScriptObjectMirror obj;
        try {
            obj = (ScriptObjectMirror) invocable.invokeFunction("validateFen", fen);
            return getReturnCode(obj);
        } catch (ScriptException  | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static ReturnCode getReturnCode(ScriptObjectMirror obj) {
        ReturnCode returnCode = new ReturnCode();
        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            if ("valid".equals(entry.getKey())) {
                returnCode.setValid((Boolean)entry.getValue());
            } else if ("error_number".equals(entry.getKey())) {
                returnCode.setCode((Integer)entry.getValue());
            } else if ("error".equals(entry.getKey())) {
                returnCode.setDescription((String)entry.getValue());
            }
        }
        return returnCode;
    }
}