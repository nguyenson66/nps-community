package npscommunity.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import npscommunity.entity.AppUser;
import npscommunity.repository.RoleRepository;
import npscommunity.repository.UserRepository;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = this.userRepo.findByUsername(username);

		if(user == null) {
			System.out.println("User not found! " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		log.info("Found User: " + user);

		List<String> roleNames = this.roleRepo.findAllRoleByUserId(user.getId());

        List<GrantedAuthority> grantList = new ArrayList<>();

        if (roleNames != null) {
            for (String role : roleNames) {
                //USER, ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
		UserDetails userDetail = new User(user.getUsername(),user.getPassword(),grantList);

		return userDetail;
	}

}
