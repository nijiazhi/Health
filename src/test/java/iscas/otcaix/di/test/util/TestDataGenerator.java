package iscas.otcaix.di.test.util;
//package ac.iscas.hrs.test.util;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import iscas.otcaix.di.dao.IArchiveBaseInfoDao;
//import iscas.otcaix.di.dao.IBasicInfoDao;
//import iscas.otcaix.di.dao.IHealthAbstractDao;
//import iscas.otcaix.di.dao.IHealthEvnIndexDao;
//import iscas.otcaix.di.dao.IInpFileMasterDao;
//import iscas.otcaix.di.entity.ArchiveBaseInfo;
//import iscas.otcaix.di.entity.HealthAbstract;
//import iscas.otcaix.di.entity.HealthBasicInfo;
//import iscas.otcaix.di.entity.HealthEvnIndex;
//import iscas.otcaix.di.entity.InpFileMaster;
//import iscas.otcaix.di.util.DateUtil;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring-servlet.xml")
//@TransactionConfiguration(defaultRollback = false)
//@Transactional
//public class TestDataGenerator {
//	@Autowired
//	private IBasicInfoDao psnMasterInfoDao;
//	@Autowired
//	private IArchiveBaseInfoDao archivebaseInfoDao;
//	@Autowired
//	private IHealthAbstractDao healthAbstractDao;
//	@Autowired
//	private IHealthEvnIndexDao healEvnIndexDao;
//	@Autowired
//	private IInpFileMasterDao inpFileMasterDao;
//	
//	@Test
//	public void generateTestData(){
//		
//	   List<HealthBasicInfo> psnInfoList =generatePsnMasterInfos();
//		for(HealthBasicInfo p:psnInfoList){
//			psnMasterInfoDao.saveOrUpdate(p);	
//		}
//	    List<ArchiveBaseInfo> archiveInfoList=generateArchiveBaseInfos();
//	    for(ArchiveBaseInfo a:archiveInfoList){
//	    	archivebaseInfoDao.saveOrUpdate(a);
//	    }
//	    
//	    List<HealthAbstract> healthAbstractList = generateHealthAbstract();
//	    for(HealthAbstract h:healthAbstractList){
//	    	healthAbstractDao.saveOrUpdate(h);
//	    }	
//	    
//	    List<HealthEvnIndex> healthEvnIndexList = generateHealthEvnIndex();
//	    for(HealthEvnIndex i:healthEvnIndexList){
//	    	healEvnIndexDao.saveOrUpdate(i);
//	    }
//	    
//	    List<InpFileMaster> inpFileMasterList = generaterInpFileMasters();
//	    for(InpFileMaster m: inpFileMasterList){
//	    	inpFileMasterDao.saveOrUpdate(m);
//	    }
//	}
//	
//	private List<HealthBasicInfo> generatePsnMasterInfos()
//    {
//		String familyName[]={"璧�","閽�","瀛�", "鏉�", "鍛�", "鍚�", "閮�", "鐜�","鍐�","闄�","鍒�", "鍗�"};
//		String familyName_py[]={"zhao","qian","sun","li","zhou","wu","zheng","wang","feng","chen","liu","wei"};
//		String givenName_male[]={"鍐�","鍑�","纾�","鏉�","瓒呰秴","蹇楅","楣忛箯","澶╄繍","鍧や簯","鍩�","鍥藉簡","鐚�"};
//		String givenName_male_py[]={"jun","kai","lei","jie","chaochao","zhifei","pengpeng","tianyun","kunyun","yu","guoqing","meng"};
//		String givenName_female[]={"鍑岃彶","闆ㄥ┓","婕Ξ","鏇肩帀","鏄熸槦","闈栫懚","鐜夌弽","鏄庣編","鑺�","鐜�","鎯�","棣�"};
//		String givenName_female_py[]={"lingfei","yuting","manni","manyu","xingxing","jingyao","yuzhen","mingmei","fang","yu","hui","xin"};
//		String countryCode[]={"1"};
//		String educationLevel[]={"0","1","2","3","4","5","6"};//鏂囩洸锛屽皬瀛︼紝鍒濅腑锛岄珮涓紝澶у锛岀澹紝鍗氬＋
//		String maritalStatusCode[]={"0","1"};//鏈锛屽凡濠�
//		String sexCode[]={"male","female"};
//		Random random=new Random();
//		List<HealthBasicInfo> list =new ArrayList<>();
//		for(int i=0;i<500;i++){
//			HealthBasicInfo psnInfo  = new HealthBasicInfo();
//			psnInfo.setArchiveDate(DateUtil.randomDate("2010-01-01", "2015-02-03"));
//			psnInfo.setBirthday(DateUtil.randomDate("1991-01-01", "2000-02-03"));
//			psnInfo.setCountryCode(countryCode[0]);
//			psnInfo.setEducationLevelCode(educationLevel[random.nextInt(7)]);
//			int sex = random.nextInt(2);
//			psnInfo.setSexCode(sexCode[sex]);
//			int family = random.nextInt(12);
//			psnInfo.setFamilyName(familyName[family]);	
//			if(sex==0){
//				psnInfo.setGivenName(givenName_male[family]);
//				psnInfo.setName(familyName[family]+givenName_male[family]);
//				psnInfo.setNamePhonetic(familyName_py[family]+givenName_male_py[family]);
//			}else{
//				psnInfo.setGivenName(givenName_female[family]);
//				psnInfo.setName(familyName[family]+givenName_female[family]);
//				psnInfo.setNamePhonetic(familyName_py[family]+givenName_female_py[family]);
//			}
//			psnInfo.setMaritalStatusCode(maritalStatusCode[random.nextInt(2)]);
//			psnInfo.setNationCode("姹�");
//			list.add(psnInfo);
//		}
//        return list;
//    }
//	
//	private List<ArchiveBaseInfo> generateArchiveBaseInfos(){
//		List<ArchiveBaseInfo> list=new ArrayList<>();
//		for(int i=0;i<500;i++){
//			ArchiveBaseInfo baseInfo = new ArchiveBaseInfo();
//			baseInfo.setPsnId(i+1);
//			baseInfo.setArchiveId(Integer.toString(1000001+i));
//			baseInfo.setArchiveDate(DateUtil.randomDate("2014-08-30", "2015-07-07"));
//			list.add(baseInfo);
//		}
//		return list;
//	}
//	
//	private List<HealthAbstract> generateHealthAbstract(){
//		 List<HealthAbstract> list = new ArrayList<>();
//		 String healthWords[]={"閰镐腑姣�","浜氾紞鏂皬缁煎悎鐥�","缂洪搧鎬ц传琛�","鑴夌鐐�","蹇冩埧绾らⅳ","鎴垮浼犲闃绘粸","鏀皵绠″摦鍠�","鏀皵绠＄値","鑳冪値","寮ユ暎鎬ц绠″嚌琛�",
//				 "鑳冩簝鐤�","鐧借鐥�","娣嬪反鐦�","楠ㄩ珦鐦�","蹇冭倢姊楁","鐢茬姸鑵哄姛鑳戒孩杩�","闃诲鎬ц偤姘旇偪","绫婚婀挎�у叧鑺傜値","璐ヨ鐥�","鑴戞姝�","鑴戣鏍�","鐧棲","绁炵粡琛板急",
//				 "绁炵粡瀹樿兘鐥�","绮剧鐥�","甯曢噾妫患鍚堢棁","鑳冪檶","鑲惧姛鑳借“绔�","绫婚婀挎�у叧鑺傜値","绱櫆"};
//		 Random random =new Random();
//		 for(int i=0;i<500;i++){
//			 HealthAbstract healthAbsc = new HealthAbstract();
//			 healthAbsc.setPsnId(i+1);
//			 int count = random.nextInt(10);
//			 Set<Integer> set = new HashSet<>();
//			 for(int j=0;j<count;j++){
//				 int index;
//				 do{
//					  index = random.nextInt(30);
//				 }while(set.contains(index));
//				 set.add(index);
//			 }
//			 StringBuilder sb =new StringBuilder();
//			 for(Integer t:set){
//				 sb.append(healthWords[t]).append(",");
//			 }
//			 if(sb.length()>0)
//				 sb.deleteCharAt(sb.length()-1);
//			 healthAbsc.setContent(sb.toString());
//			 
//			 list.add(healthAbsc);
//		 }
//		 return list;
//	}
//	
//	private List<HealthEvnIndex> generateHealthEvnIndex(){
//		List<HealthEvnIndex> list = new ArrayList<>();
//		String encounterType[]={"HRP01.01","HRP02.01","HRP03.01","HRP04.01","HRP05.01"};
//		String encounterName[]={"闂ㄦ�ヨ瘖鍙�","浣忛櫌","鐤楀吇","鍋ュ悍浣撴","浼犳煋鐥呮姤鍛婂崱"};
//		Random random = new Random();
//		Set<Integer> set = new HashSet<>();
//		for(int i=0;i<400;i++){
//			int psnId;
//			do{
//				psnId = random.nextInt(500)+1;
//			}while(set.contains(psnId));			
//			set.add(psnId);		
//			HealthEvnIndex evnIndex = new HealthEvnIndex();
//			evnIndex.setPsnId(psnId);
//			evnIndex.setHealthEvnId(i+1);
//			evnIndex.setArchiveId(Integer.toString(1000000+psnId));
//			evnIndex.setVisitDate(DateUtil.randomDate("2005-01-30", "2014-12-30"));
//			evnIndex.setEncounterType(encounterType[1]);
//			evnIndex.setEncounterName(encounterName[1]);
//			list.add(evnIndex);
//		}
//		return list;
//	}
//	
//	private List<InpFileMaster> generaterInpFileMasters(){
//		
//		String admissionDesc[]={"鍙嶅鍜冲椊10骞达紝杩涜鎬у懠鍚稿洶闅�3骞达紝鍐嶅彂2澶�","鍜冲椊锛屽挸鐥�20骞达紝鍔犻噸涓ゅ懆锛屽彂鐑�1鍛紝绁炲織鎭嶆儦1澶╁叆闄�","鍏ㄨ韩鏁ｅ湪鎬х毊鐤逛即棰滈潰娴偪涓�灏忔椂","鍙戠儹2澶╋紝鍜冲椊1鍛�",
//				"鍜冲椊浼村悙娌�佸枆涓棸鍝�3澶╋紝鍔犻噸1澶�","杞︾ジ鑷磋叞閮紝鍙冲ぇ鑵跨柤鐥�2灏忔椂","鍙戠幇宸︿晶鑵硅偂娌熷尯鍙鎬у寘鍧�10骞翠綑锛屼笉鑳藉洖绾�11灏忔椂","鍙戠幇鍓嶉閮ㄨ偪澶с�佷即椋熸浜㈣繘銆佷箯鍔�9骞翠綑","妫�鏌瓒呭彂鐜板乏渚х敳鐘惰吅鍖呭潡2澶�","鍙充笅鑵圭棝4澶╋紝鍔犻噸1澶�"};
//		String admissionWayCode[]={"1","2","3"};
//		String admissionWayDesc[]={"闂ㄨ瘖","鎬ヨ瘖","杞櫌"};
//		String admissionConditionCode[]={"1","2","3"};
//		String admissionConditionDesc[]={"鍗�","鎬�","涓�鑸�"};
//		String physical[]={"蹇冪數鍥�","X鍏�","B瓒�","琛�妫�","灏挎","琛�鑴�","楠ㄥ瘑搴︾洃娴�","鑲濆姛","鍐呯妫�鏌�","琛�绯�"};
//		String deptAdmissionToName[]={"鑰抽蓟鍠夌","娉屽翱绉�","鐨偆绉�","蹇冭剰鐥呯","楠ㄧ","绁炵粡绉�","濡囩","鐥呯悊绉�","澶栫","楹婚唹绉�"};
//		String causesOfDeathCode[]={"AKBDGR","JXBBHTB","MDXXZB","CEXZL","SGEXZL","QMB","GXJSB","HTXYZHT","SMSJWCY","ZDXLJ"};
//		String causesOfDeathName[]={"鍩冨彲鐥呮瘨鎰熸煋","宸ㄧ粏鑳炲寘娑典綋鐥�","姊呮瘨鎬у績鑴忕梾","鍞囨伓鎬ц偪鐦�","椋熺鎭舵�ц偪鐦�","鐞冮夯鐥�","鑲濇�ц剨楂撶梾","鍚庡ぉ鎬ц叞妞庢粦鑴�","娌欓棬姘忚弻鑳冭偁鐐�","涓瘨鎬х棦鐤�"};
//		String injuryIncidentCode[]={"Z51.124","Z51.134","Z51.141","Z00.001","Z01.804Z04.804","Z51.804","Z51.004","Z46.201","Z47.002","Z43.001"};
//		String injuryIncidentDesc[]={"鑲濈檶鍖栫枟","鑲夌槫鍖栫枟","榧诲捊鐧屽寲鐤�","鍋ュ悍鏌ヤ綋","绔嬩綅鑰愬姏妫�鏌�","鑲跨槫鍏嶇柅鍒跺墏娌荤枟","涔崇檶鏀剧枟","瀹夎浜哄伐鑰宠湕","楠ㄦ姌鍙栭拤","姘旂鍒囧紑鏈悗鎷旂"};
//		String actionCode[]={"娌绘剤","濂借浆","鏈不","鏃犳晥","姝讳骸","鍏朵粬"};
//		String bloodType[]={"A","B","O","AB","涓嶈"};
//		String bloodTypeRh[]={"闃�","闃�"};
//		Random random = new Random();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
//		List<InpFileMaster> list=new ArrayList<>();
//		for(int i=1;i<=400;i++){
//			InpFileMaster fileMaster = new InpFileMaster();
//			fileMaster.setHealthEvnId(i);
//			fileMaster.setTimesOfHospitalization(random.nextInt(10));
//			fileMaster.setAdmissionDateTime(DateUtil.randomDate("2005-01-30", "2014-12-30"));
//			int r1=random.nextInt(10);
//			fileMaster.setAdmissionDesc(admissionDesc[r1]);
//			int r2=random.nextInt(3);
//			fileMaster.setAdmissionWayCode(admissionWayCode[r2]);
//			fileMaster.setAdmissionWayDesc(admissionWayDesc[r2]);
//			int r3=random.nextInt(3);
//			fileMaster.setAdmissionConditionCode(admissionConditionCode[r3]);
//			fileMaster.setAdmissionConditionDesc(admissionConditionDesc[r3]);
//			
//			fileMaster.setDiagnoseDate(DateUtil.randomDate("2005-01-30", "2014-12-30"));
//			fileMaster.setDiagnoseDays(DateUtil.getIntervalDays(fileMaster.getDiagnoseDate(), new Date()));
//			
//			int r4=random.nextInt(10);
//			fileMaster.setPhysical(physical[r4]);
//			
//			int r5=random.nextInt(10);
//			fileMaster.setDeptAdmissionToName(deptAdmissionToName[r5]);
//			
//			fileMaster.setHospitalInfectionFlag(Integer.toString(random.nextInt(2)));
//			
//			fileMaster.setAllergyMedicationsFlag(Integer.toString(random.nextInt(2)));
//			
//			int r6=random.nextInt(50);
//			if(r6<34&&r6>29){//闅忔満鎸囧畾鏌愪汉姝讳骸
//				fileMaster.setDeathDateTime(DateUtil.randomDate("2005-01-30", "2014-12-30"));
//				fileMaster.setIsAutopsy(Integer.toString(random.nextInt(2)));
//				int r6_1 = random.nextInt(10);
//				fileMaster.setCausesOfDeathCode(causesOfDeathCode[r6_1]);
//				fileMaster.setCausesOfDeathName(causesOfDeathName[r6_1]);
//			}
//			
//			int r7=random.nextInt(60);
//			if(r7>30&&r7<35){
//				int r7_1 = random.nextInt(10);
//				fileMaster.setInjuryIncidentCode(injuryIncidentCode[r7_1]);
//				fileMaster.setInjuryIncidentDesc(injuryIncidentDesc[r7_1]);
//			}
//			
//			fileMaster.setDischargeDateTime(DateUtil.randomDate(format.format(fileMaster.getAdmissionDateTime()), format.format(new Date())));
//			fileMaster.setHospitalDays(DateUtil.getIntervalDays((fileMaster.getAdmissionDateTime()),fileMaster.getDischargeDateTime()));
//			
//			int r8=random.nextInt(6);
//			fileMaster.setActionCode(actionCode[r8]);
//			
//			int r9=random.nextInt(5);
//			fileMaster.setBloodType(bloodType[r9]);
//			
//			int r10=random.nextInt(2);
//			fileMaster.setBloodTypeRh(bloodTypeRh[r10]);
//			
//			int r11=random.nextInt(2);
//			fileMaster.setTransfusionFlag(Integer.toString(r11));
//			if(r11==1){
//				fileMaster.setTransfusionTimes(random.nextInt(5)+1);
//				int r11_1=random.nextInt(2);
//				fileMaster.setAutoTransfusionFlag(Integer.toString(r11_1));
//				if(r11_1==1){
//					fileMaster.setAutoTransfusionVolumn(random.nextInt(100)+100);
//				}
//				int r11_2=random.nextInt(2);
//				fileMaster.setTransfusionReactions(Integer.toString(r11_2));
//				if(r11_2==1){
//					fileMaster.setTransfusionReactionsTimes(random.nextInt(5)+1);
//				}
//			}
//			
//			fileMaster.setWasDyingDays(random.nextInt(5));
//			
//			fileMaster.setSeriouslyIllDays(random.nextInt(5));
//			
//			int r12 = random.nextInt(5);
//			fileMaster.setRescueNumber(r12);
//			fileMaster.setRescueSuccessNumber(r12);
//			
//			fileMaster.setIcuDays(random.nextInt(5));
//			
//			fileMaster.setCcuDays(random.nextInt(5));
//			
//			fileMaster.setSpecialCareDays(random.nextInt(5));
//			
//			fileMaster.setPrimaryCareDays(random.nextInt(5));
//			
//			fileMaster.setSecondaryCareDays(random.nextInt(5));
//			
//			fileMaster.setOccurrenceBedsoresViews(random.nextInt(3));
//			
//			list.add(fileMaster);
//			
//		}
//		
//		return list;
//	}
//
//
//}
