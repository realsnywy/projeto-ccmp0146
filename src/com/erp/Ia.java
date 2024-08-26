package com.erp;

public class Ia {

   
    public Estoque estoque;
    public int quantiadeDeProduto;
    public float nivelDoEstoque;

    public float peso;
    public float pesoMin;
    public float pesoSuportadoNoEstoque;
    public float pesoTotaldoProduto;

    public Ia(Estoque estoque, float peso, float pesoSuportadoNoEstoque){
        this.estoque = estoque;
        this.peso = peso;
        this.pesoSuportadoNoEstoque = pesoSuportadoNoEstoque;
    }
    
    public float getPeso(){
        return peso;
    }

    public float calcularPesoTotal(String id){
        int idDeBusca = Integer.parseInt(id);
        pesoTotaldoProduto = estoque.produtos.get(idDeBusca).getQuantidade() * peso;
        return pesoTotaldoProduto;
    }

    public String consultaDeInfo(){
        pesoMin = (float) (pesoSuportadoNoEstoque * 0.4);
        nivelDoEstoque = (pesoTotaldoProduto * 100)/pesoSuportadoNoEstoque;

        if (pesoTotaldoProduto <= pesoMin){
            return "Estoque do produto em níveis baixos (" + nivelDoEstoque + "% da capacidade sendo utilizada), por favor solicite abastecimento.";
        }
        else{
             
            return "O produto está em " + nivelDoEstoque + "% no estoque.";
        }

    }










    
}
