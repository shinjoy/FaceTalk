package kr.nomad.mars;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import kr.nomad.mars.dao.UserItemDao;
import kr.nomad.mars.dto.UserItem;
import kr.nomad.util.T;

@Service
public class SchedulerController {
	
@Autowired UserItemDao useritemDao;	
	
	//@Scheduled(cron = "30 * * * * ?") // 매분실행
	/*public void sendShcooltype() {
		System.out.println("ddd");
		List<UserItem>list = useritemDao.getUseruseItemList();
		for(int i=0;i<list.size();i++){
			UserItem ui = list.get(i);
			
			String day = ui.getFinishDate();
			
			String today = T.getNow();//20151210153810
			//String finish = ui.getFinishDate().substring(0, 19);//2015-12-16 12:40:00
			String finish =day.substring(0,4)+day.substring(5,7)+day.substring(8,10)
						   +day.substring(11,13)+day.substring(14,16)+day.substring(17,19);
			int chk = T.getDurationByMinute(today,finish);
			
			if(chk<0){
				useritemDao.updateUserItem(ui.getItemSeq(),0);
				
			}
		}
    }*/
}
