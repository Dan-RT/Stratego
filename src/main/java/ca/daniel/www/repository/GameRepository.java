package ca.daniel.www.repository;

import ca.daniel.www.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {
    Game findBy_id(String id);
}

