package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundShortMessage;

/**
 * Created by max on 2017/8/21.
 */
public interface FundShortMessageRepository  extends JpaRepository<FundShortMessage,Integer>{
}
