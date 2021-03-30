package tn.esprit.spring.Aspect;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class SchedulerSubject {
	@Scheduled(fixedRate = 6000)
	   public void fixedSubSch() {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	      Date now = new Date();
	      String strDate = sdf.format(now);
	      System.out.println("Fixed schedulerSubject:: " + strDate);

}
	}
