package com.global.system.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.system.Dao.UserDao;
import com.global.system.Exceptions.MyException;
import com.global.system.TimeUtilsMethods.LocalDateToString;
import com.global.system.TimeUtilsMethods.MinuteDiff;
import com.global.system.TimeUtilsMethods.StrignDateToLocalDateTime;
import com.global.system.model.CheckJWTModel;
import com.global.system.model.FileModel;
import com.global.system.model.UserLoginHistoryModel;
import com.global.system.model.UserModel;
import com.global.system.repository.FileRepository;
import com.global.system.repository.JwtRepository;
import com.global.system.repository.UserHistoryRepository;
import com.global.system.repository.Userrepository;
import com.global.system.utils.HashCodeGenearate;
import com.global.system.utils.PassBasedEnc1;

@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    Userrepository userrepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    JwtRepository jwtRepository;

    @Autowired
    UserHistoryRepository userHistoryRepository;

    public int regUser(UserModel userModel) throws MyException, SQLException {

        String email = userModel.getEmailId();
        int i = 0;
        List<UserModel> emails = userrepository.findByEmailId();

        if (emails == null || emails.isEmpty()) {

            i = insertUser(userModel);

        } else {

            boolean b = emails
                    .stream()
                    .filter(s -> s.getEmailId().equalsIgnoreCase(email))
                    .collect(Collectors.toList())
                    .isEmpty();

            i = b? (insertUser(userModel)): 10;
        

        }
        if (i != -1) {
            return i;
        } else {

            throw new MyException("user register not success");
            
            
        }

    }
   
    public int insertUser(UserModel userModel) {
        UserModel um = userrepository.save(userModel);
        return (um == null) ? -1 : 1;
        
    }

    public int getBlockService() {

        return userrepository.getLastFileId();
      
    }

    public FileModel getFilesToImplementBlockChain(int fileId) {
       
        return  fileRepository.findById(fileId).get();
    }

    public String  checkSmartContract(UserModel userModel){

        String message=null;
        List<UserLoginHistoryModel> ulhm= userHistoryRepository.findByUserId(userModel.getUserId());
       
        if(ulhm.size()==0){
            String hash= HashCodeGenearate.getHashValue(userModel.getPassword());
            UserLoginHistoryModel ulm=new UserLoginHistoryModel();
            ulm.setOldHash(hash);
            ulm.setNewHash("First hash");
            ulm.setUserId(userModel.getUserId());
            ulm.setLoginTime(LocalDateToString.convertToSring(LocalDateTime.now()));
            userHistoryRepository.save(ulm);
            message="smart";

        }else{
            UserLoginHistoryModel user1 = ulhm.stream()
                        .max(Comparator.comparingInt(UserLoginHistoryModel::getHistoryId))
                        .get();
               String oldHash=HashCodeGenearate.getHashValue(userModel.getPassword());
               String newHash=HashCodeGenearate.getHashValue(userModel.getPassword());
               String s[]=user1.getOldHash().split(",");
             
               if(PassBasedEnc1.verifyUserPassword(userModel.getPassword(),s[1],s[0])){
                UserLoginHistoryModel ulm=new UserLoginHistoryModel();
            ulm.setOldHash(oldHash);
            ulm.setNewHash(newHash);
            ulm.setUserId(userModel.getUserId());
            ulm.setLoginTime(LocalDateToString.convertToSring(LocalDateTime.now()));
            userHistoryRepository.save(ulm);
            message=newHash;

               }  else{
                message="modified";

               }       
 
        }

        return message;

    }


    public long checkJWTtoken(int userId){

        long i=-1;
        try{
        CheckJWTModel cJwtModel= jwtRepository.findByUserId(userId);

        LocalDateTime ldt= StrignDateToLocalDateTime.convertToDate(cJwtModel.getEndTime());

        LocalDateTime ldt1= LocalDateTime.now();

      i=  MinuteDiff.minDiff(ldt, ldt1);
        }catch(Exception e){
           
            i=-1;
        }

        return  i;
    }


}
