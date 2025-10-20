package org.serratec.SerratecMusic.repository;

import org.serratec.SerratecMusic.entity.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long>{

}
