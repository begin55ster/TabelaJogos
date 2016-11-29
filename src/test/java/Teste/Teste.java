package Teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.jefferson.Model.Jogador;
import br.com.jefferson.Model.Time;

public class Teste {

	public static void main(String[] args) {
		
		EntityManagerFactory factory =  Persistence.createEntityManagerFactory("jogos");
		EntityManager em = factory.createEntityManager();
		
		Jogador jogador = new Jogador();
		jogador.setNomeJogador("Rubens de paiva");
		jogador.setTimeQueTorce("Corinthians");
		jogador.setDdd("61");
		jogador.setTelefone("33754338");
		jogador.setEmail("rubens@gmail.com");
		
		Time time0 = new Time();
		time0.setNomeTime("Corinthians");
		time0.setJogador(jogador);
		Time time1 = new Time();
		time1.setNomeTime("Palmeiras");
		Time time2 = new Time();
		time2.setNomeTime("São Paulo");
		Time time3 = new Time();
		time3.setNomeTime("Santos");

				em.getTransaction().begin();
				em.persist(jogador);
				em.persist(time0);

				em.getTransaction().commit();

	}

}
