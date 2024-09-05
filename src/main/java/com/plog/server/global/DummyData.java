package com.plog.server.global;

import com.plog.server.badge.domain.Badge;
import com.plog.server.badge.domain.MyBadge;
import com.plog.server.badge.repository.BadgeRepository;
import com.plog.server.badge.repository.MyBadgeRepository;
import com.plog.server.plogging.domain.Activity;
import com.plog.server.plogging.domain.Location;
import com.plog.server.plogging.repository.ActivityRepository;
import com.plog.server.plogging.repository.LocationRepository;
import com.plog.server.post.domain.Post;
import com.plog.server.post.repository.PostRepository;
import com.plog.server.profile.domain.Profile;
import com.plog.server.profile.repository.ProfileRepository;
import com.plog.server.user.domain.User;
import com.plog.server.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DummyData {
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final ProfileRepository profileRepository;
    private final BadgeRepository badgeRepository;
    private final MyBadgeRepository myBadgeRepository;
    private final ActivityRepository activityRepository;
    private final PostRepository postRepository;

    @PostConstruct
    public void init (){
        User user = User.builder()
                .userAccount("user")
                .userPw("1234")
                .userEmail("email")
                .userUUID(UUID.randomUUID())
                .build();
        userRepository.save(user);

        Badge badge = Badge.builder()
                .badgeGoal("기본 배지")
                .cost(0)
                .build();
        badgeRepository.save(badge);

        Profile profile = Profile.builder()
                .userNickname("메롱")
                .user(user)
                .badge(badge)
                .totalTrash(0)
                .totalTime(0.0)
                .totalCoin(0)
                .totalDistance(0.0)
                .userMembership(false)
                .ploggingStatus(false)
                .build();
        profileRepository.save(profile);

        MyBadge myBadge = MyBadge.builder()
                .badge(badge)
                .profile(profile)
                .build();
        myBadgeRepository.save(myBadge);

        log.info("주입성공");

        Location startlocation = Location.builder()
                .profile(profile)
                .longitude(126.91542467032245)
                .latitude(37.598769835423475)
                .build();

        locationRepository.save(startlocation);

        Location location = Location.builder()
                .profile(profile)
                .longitude(126.91534860669759)
                .latitude(37.59844091925552)
                .build();

        locationRepository.save(location);

        Location location2 = Location.builder()
                .profile(profile)
                .longitude(126.91527253863138)
                .latitude(37.59811650797162)
                .build();

        locationRepository.save(location2);

        Location location3 = Location.builder()
                .profile(profile)
                .longitude(126.91508093028813 )
                .latitude(37.59732800321048)
                .build();

        locationRepository.save(location3);

        Location endlocation = Location.builder()
                .profile(profile)
                .longitude(126.91504295184949)
                .latitude(37.59587287526493)
                .build();

        locationRepository.save(endlocation);

        log.info("위치 정보 저장");

        Post post = Post.builder()
                .title("더미 포스트 제목 ")
                .content("이것은 더미 포스트 내용입니다. 포스트 번호: ")
                .joinCount(0)
                .likeCount(0)
                .plogPlace("장소 " )
                .meetPlace("모임 장소 " )
                .time(LocalDate.now())
                .schedule("2024-09-01")
                .profile(profile) // Associate the post with the created profile
                .build();
        postRepository.save(post);
        log.info("더미 포스트 데이터 저장 완료");

    }

}
