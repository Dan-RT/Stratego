package ca.daniel.www.repository;

import ca.daniel.www.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {
    Player findBy_id(String id);
}
