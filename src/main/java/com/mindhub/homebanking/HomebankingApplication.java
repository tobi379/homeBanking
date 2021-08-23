package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.List;


@SpringBootApplication
public class HomebankingApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;



	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);




	}
	@Bean
	public CommandLineRunner initData(ClientRepository repository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {
		return (args) -> {


			Client cliente = repository.save(new Client("Melba","Morel","melba@mindhub.com",passwordEncoder.encode("123")));
			Client admin = repository.save(new Client("admin", "admin", "admin", passwordEncoder.encode("admin")));

			Account account1 = accountRepository.save(new Account("VIN001", LocalDateTime.now(),0,cliente,AccountType.Ahorro,true));
			Account account2 = accountRepository.save(new Account("VIN002", LocalDateTime.now().plusDays(1),0,cliente,AccountType.Corriente,true));

			Loan loan1 =loanRepository.save(new Loan("Hipotecario", 500000., List.of(12,24,36,48,60),0.30));
			Loan loan2 =loanRepository.save(new Loan("Personal", 100000., List.of(6,12,24),0.15));
			Loan loan3 =loanRepository.save(new Loan("Automotriz", 300000., List.of(6,12,24,36),0.20));

			cardRepository.save(new Card(CardColor.GOLD,"1000-0000-0000-0001",101,LocalDate.now(),LocalDate.now().plusYears(5), cliente.getFirstName() + " " +cliente.getLastName(),CardType.DEBIT,cliente, true));
			cardRepository.save(new Card(CardColor.TITANIUM,"1000-0000-0000-0002",102,LocalDate.now(),LocalDate.now().plusYears(5), cliente.getFirstName()+ " " + cliente.getLastName(),CardType.CREDIT,cliente, true));


		};
	}
}
