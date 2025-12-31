package br.com.mardevsystem.repository;

import br.com.mardevsystem.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange,Long> {

    Exchange findByFromAndTo(String from, String to);

}
