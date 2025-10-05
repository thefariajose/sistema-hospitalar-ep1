package main.utils;

import java.io.*;
import java.util.*;

public class ArquivoUtils {
    public static void salvarEmCSV(String nomeArquivo, List<String> linhas){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))){
            for(String linha : linhas){
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }



    public static List<String> carregarDeCSV(String caminhoArquivo) {
        List<String> linhas = new ArrayList<>();
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            return linhas; // retorna lista vazia se não existir
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }

        return linhas;
    }
}

