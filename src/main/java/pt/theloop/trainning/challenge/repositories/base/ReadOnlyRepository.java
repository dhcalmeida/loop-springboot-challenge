package pt.theloop.trainning.challenge.repositories.base;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface ReadOnlyRepository<T, I> extends Repository<T, I> {

    List<T> findAll();

}
