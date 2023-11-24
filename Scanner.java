package com.mycompany.lexical_analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    private static final Map<String, TipoToken> palabrasReservadas;

    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("and",    TipoToken.AND);
        palabrasReservadas.put("else",   TipoToken.ELSE);
        palabrasReservadas.put("false",  TipoToken.FALSE);
        palabrasReservadas.put("for",    TipoToken.FOR);
        palabrasReservadas.put("fun",    TipoToken.FUN);
        palabrasReservadas.put("if",     TipoToken.IF);
        palabrasReservadas.put("null",   TipoToken.NULL);
        palabrasReservadas.put("or",     TipoToken.OR);
        palabrasReservadas.put("print",  TipoToken.PRINT);
        palabrasReservadas.put("return", TipoToken.RETURN);
        palabrasReservadas.put("true",   TipoToken.TRUE);
        palabrasReservadas.put("var",    TipoToken.VAR);
        palabrasReservadas.put("while",  TipoToken.WHILE);
    }

    private final String source;

    private final List<Token> tokens = new ArrayList<>();
    
    public Scanner(String source){
        this.source = source + " ";
    }

    public List<Token> scan() throws Exception {
        int estado = 0;
        String lexema = "";
        char c;

        for(int i=0; i<source.length(); i++){
            c = source.charAt(i);


            switch (estado){
                case 0 -> {
                    if(Character.isLetter(c)){
                        estado = 13;
                        lexema += c;
                    }else if(Character.isDigit(c)){
                        estado = 15;
                        lexema += c;
                        /*while(Character.isDigit(c)){
                        lexema += c;
                        i++;
                        c = source.charAt(i);
                        }
                        Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema));
                        lexema = "";
                        estado = 0;
                        tokens.add(t);
                        */
                    }else if(c == '>'){
                        estado = 1;
                        lexema += c;
                    }else if(c == '<'){
                        estado = 4;
                        lexema += c;
                    }else if(c == '='){
                        estado = 7;
                        lexema += c;
                    }else if(c == '!'){
                        estado = 10;
                        lexema += c;
                    }else if (c == '"'){
                        estado = 24;
                        lexema += c;
                    }else if (c == '/'){
                        estado = 26;
                        lexema += c;
                    }else if(c == '('){
                        lexema += c;
                        Token t = new Token(TipoToken.LEFT_PAREN, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                    }else if(c == '{'){
                        lexema += c;
                        Token t = new Token(TipoToken.LEFT_BRACE, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                    }else if(c == ')'){
                        lexema += c;
                        Token t = new Token(TipoToken.RIGHT_PAREN, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";                        
                    }else if(c == '}'){
                        lexema += c;
                        Token t = new Token(TipoToken.RIGHT_BRACE, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";                        
                    }else if(c == ','){
                        lexema += c;
                        Token t = new Token(TipoToken.COMMA, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                    }else if(c == '.'){
                        lexema += c;
                        Token t = new Token(TipoToken.DOT, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                    }else if(c == '-'){
                        lexema += c;
                        Token t = new Token(TipoToken.MINUS, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";                       
                    }else if(c == '+'){
                        lexema += c;
                        Token t = new Token(TipoToken.PLUS, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                    }else if(c == ';'){
                        lexema += c;
                        Token t = new Token(TipoToken.SEMICOLON, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";                       
                    }else if(c == '*'){
                        lexema += c;
                        Token t = new Token(TipoToken.STAR, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";                        
                    }
                }
                    
                case 1 -> {
                    if(c == '='){ //Case 2
                        lexema += c;
                        Token t = new Token(TipoToken.GREATER_EQUAL, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                    }
                    else{ //Case 3
                        Token t = new Token(TipoToken.GREATER, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                }
                    
                case 4 -> {
                    if(c == '='){ //Case 5
                        lexema += c;
                        Token t = new Token(TipoToken.LESS_EQUAL, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                    }else{ //Case 6
                        Token t = new Token(TipoToken.LESS, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                }
                    
                case 7 -> {
                    if(c == '='){ //Case 8
                        lexema += c;
                        Token t = new Token(TipoToken.EQUAL_EQUAL, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                    }else{ //Case 9
                        Token t = new Token(TipoToken.EQUAL, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                }
                    
                case 10 -> {
                    if(c == '='){ //Case 11
                        lexema += c;
                        Token t = new Token(TipoToken.BANG_EQUAL, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                    }else{ //Case 12
                        Token t = new Token(TipoToken.BANG, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                }

                case 13 -> {
                    if(Character.isLetterOrDigit(c)){
                        estado = 13;
                        lexema += c;
                    }
                    else{
                        TipoToken tt = palabrasReservadas.get(lexema);

                        if(tt == null){ //Case 14
                            Token t = new Token(TipoToken.IDENTIFIER, lexema);
                            tokens.add(t);
                        }
                        else{
                            Token t = new Token(tt, lexema);
                            tokens.add(t);
                        }
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                }

                case 15 -> {
                    if(Character.isDigit(c)){
                        estado = 15;
                        lexema += c;
                    }
                    else if(c == '.'){
                        estado = 16;
                        lexema += c;
                    }
                    else if(c == 'E'){
                        estado = 18;
                        lexema += c;
                    }
                    else{ //Case 22
                        Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema));
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                }
                
                case 16 -> {
                    if(Character.isDigit(c)){
                        estado = 17;
                        lexema += c;
                    }else{
                        Interprete.error(1, "Debe seguir un Digito despues del punto");
                        System.exit(0);
                    }
                }
                
                case 17 ->{
                    if(Character.isDigit(c)){
                        estado = 17;
                        lexema += c;
                    }else if( c == 'E' || c == 'e'){
                        estado = 18;
                        lexema += c;
                    }else{ //Case 23
                        Token t = new Token(TipoToken.NUMBER, lexema, Float.valueOf(lexema));
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                }
                
                case 18 -> {
                    if(c == '+' || c== '-'){
                        estado = 19;
                        lexema += c;
                    }else if(Character.isDigit(c)){
                        estado = 20;
                        lexema += c;
                    }else{
                        Interprete.error(1, "Debe seguir otro digito o un simbolo + , - despues de la E");
                        System.exit(0);
                    }
                }
                
                case 19 ->{
                    if(Character.isDigit(c)){
                        estado = 20;
                        lexema += c;
                    }else{
                        Interprete.error(1, "Debe seguir un Digito despues del + o -");
                        System.exit(0);
                    }
                }
                
                case 20 -> {
                    if(Character.isDigit(c)){
                        estado = 20;
                        lexema += c;
                    }else{ //Case 21
                        Token t = new Token(TipoToken.NUMBER, lexema, String.valueOf(lexema) );
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                }
                
                case 24 -> {
                    if(c == '"'){ //Case 25 
                        lexema += c;
                        Token t = new Token(TipoToken.STRING, lexema, lexema.substring(1, lexema.length() - 1));
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                        
                    }else if(c == '\n' || c == '\r'){
                        Interprete.error(1, "Una cadena no puede contener saltos de linea");
                        System.exit(0);
                    }else{
                        estado = 24;
                        lexema += c;
                    }
                
                }
                
                case 26 -> {
                    if(c == '/'){
                        estado = 30;
                        lexema += c;
                    }else if(c == '*'){
                        estado = 27;
                        lexema += c;
                    }else{
                        Token t = new Token(TipoToken.SLASH, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                }
                
                case 27 ->{
                    if(c == '*'){
                        estado = 28;
                        lexema += c;
                    }else{
                        estado = 27;
                        lexema += c;
                    }
                }
                
                case 28 ->{
                    if(c == '*'){
                        estado = 28;
                        lexema += c;
                    }else if(c == '/'){ //Case 29
                        estado = 0;
                        lexema = "";
                    }else{
                        estado = 27;
                        lexema += c;
                    }
                }
                
                case 30 ->{
                    if(c == '\r' || c == '\n'){
                        estado = 0;
                        lexema = "";
                    }else{
                        estado = 30;
                        lexema += c;
                    }
                }
            }


        }


        return tokens;
    }
}