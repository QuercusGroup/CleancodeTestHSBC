package hsbc.codetest.libraryusersms.bss;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hsbc.codetest.libraryusersms.bss.dto.UserDto;
import hsbc.codetest.libraryusersms.bss.exception.RecordAlreadyExist;
import hsbc.codetest.libraryusersms.bss.exception.RecordNotFound;
import hsbc.codetest.libraryusersms.data.UserDataService;
import hsbc.codetest.libraryusersms.data.entity.RecordStatusEnum;
import hsbc.codetest.libraryusersms.data.entity.UserEntity;

@Service
public class UserService {

	@Autowired
	private UserDataService userDataService;

	@Autowired
	private UserDataMapper userDataMapper;

	public UserDto saveUser(UserDto User) {
		if (User.getId() != null && userDataService.existsById(User.getId())) {
			throw new RecordAlreadyExist(User.getId().toString());
		} else {
			User.setId(null);
		}
		UserEntity UserEntity = userDataMapper.mapUserDtoToUserEntity(User);

		UserEntity savedUserRecord = userDataService.save(UserEntity);

		return userDataMapper.mapUserEntityToUserDto(savedUserRecord);
	}

	public UserDto updateOrCreateUser(UserDto user) {
		UserEntity userEntity;

		if (user.getId() != null) {
			userEntity = userDataService.findById(user.getId())
					.orElseThrow(() -> new RecordNotFound(user.getId().toString()));
			userEntity = userDataMapper.mapUserDtoToUserEntity(user, userEntity);

		} else {
			userEntity = userDataMapper.mapUserDtoToUserEntity(user);
		}

		UserEntity savedUserRecord = userDataService.save(userEntity);
		return userDataMapper.mapUserEntityToUserDto(savedUserRecord);
	}

	public UserDto getById(Long id) {
		UserEntity user = userDataService.findById(id).orElseThrow(() -> new RecordNotFound(id.toString()));
		return userDataMapper.mapUserEntityToUserDto(user);
	}

	public UserDto deleteUser(Long id) {
		UserEntity user = userDataService.findById(id).orElseThrow(() -> new RecordNotFound(id.toString()));
		userDataService.delete(user);
		return userDataMapper.mapUserEntityToUserDto(user);
	}

	public UserDto deleteUserLogical(Long id) {
		UserEntity user = userDataService.findById(id).orElseThrow(() -> new RecordNotFound(id.toString()));
		user.setRecordStatus(RecordStatusEnum.DELETED);
		userDataService.save(user);
		return userDataMapper.mapUserEntityToUserDto(user);
	}

	public List<UserDto> getAllRecords() {
		List<UserDto> list = new ArrayList<>();
		userDataService.findAll().forEach(p-> list.add(userDataMapper.mapUserEntityToUserDto(p)));
		return list;
	}

}
