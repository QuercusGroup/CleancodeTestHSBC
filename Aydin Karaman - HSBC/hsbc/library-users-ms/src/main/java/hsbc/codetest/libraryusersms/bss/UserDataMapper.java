package hsbc.codetest.libraryusersms.bss;

import org.springframework.stereotype.Service;

import hsbc.codetest.libraryusersms.bss.dto.UserDto;
import hsbc.codetest.libraryusersms.data.entity.UserEntity;

@Service
public class UserDataMapper {

	public UserEntity mapUserDtoToUserEntity(UserDto user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setName(user.getName());
		userEntity.setSurname(user.getSurname());
		userEntity.setBirthdate(user.getBirthdate());
		return userEntity;
	}

	public UserDto mapUserEntityToUserDto(UserEntity userEntity) {
		UserDto user = new UserDto();
		user.setId(userEntity.getId());
		user.setName(userEntity.getName());
		user.setSurname(userEntity.getSurname());
		user.setBirthdate(userEntity.getBirthdate());
		return user;
	}

	public UserEntity mapUserDtoToUserEntity(UserDto user, UserEntity userEntity) {
		userEntity.setName(user.getName());
		userEntity.setSurname(user.getSurname());
		userEntity.setBirthdate(user.getBirthdate());

		return userEntity;
	}

}
