import java.util.*;

public class Cliente {
    private String nome;
    private List<Aluguel> alugueis = new ArrayList<>();

    public Cliente(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public void adicionaAluguel(Aluguel aluguel) {
        alugueis.add(aluguel);
    }

    public String extrato() {
        double valorTotal = 0;
        int pontosDeAlugadorFrequente = 0;
        StringBuilder resultado = new StringBuilder("Registro de Alugueis de " + getNome() + "\n");

        for (Aluguel aluguel : alugueis) {
            double valorCorrente = calcularValor(aluguel);

            pontosDeAlugadorFrequente++;
            if ((aluguel.getFita().getCodigoDePreco() == Fita.LANCAMENTO) 
                && aluguel.getDiasAlugada() > 1) {
                pontosDeAlugadorFrequente++;
            }

            resultado.append("\t").append(aluguel.getFita().getTitulo())
                     .append("\t").append(valorCorrente).append("\n");
            valorTotal += valorCorrente;
        }

        resultado.append("Valor total devido: ").append(valorTotal).append("\n");
        resultado.append("Você ganhou ").append(pontosDeAlugadorFrequente)
                 .append(" pontos de alugador frequente");
        return resultado.toString();
    }

    private double calcularValor(Aluguel aluguel) {
        double valorCorrente = 0;
        switch (aluguel.getFita().getCodigoDePreco()) {
            case Fita.NORMAL:
                valorCorrente += 2;
                if (aluguel.getDiasAlugada() > 2)
                    valorCorrente += (aluguel.getDiasAlugada() - 2) * 1.5;
                break;
            case Fita.LANCAMENTO:
                valorCorrente += aluguel.getDiasAlugada() * 3;
                break;
            case Fita.INFANTIL:
                valorCorrente += 1.5;
                if (aluguel.getDiasAlugada() > 3)
                    valorCorrente += (aluguel.getDiasAlugada() - 3) * 1.5;
                break;
        }
        return valorCorrente;
    }
}
