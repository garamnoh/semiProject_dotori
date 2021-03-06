package com.friend.model.vo;

import java.sql.Date;

public class Friend {
	
	private String followee;
	private String follower;
	private String friendshipName;
	private int visitCount;
	private String followeeProfilePath;
	private String followerProfilePath;
	private String followerProfileName;
	private String followerProfilePhone;
	
	private String gender;
	private String address;
	private Date birth;
	
	public Friend() {
		// TODO Auto-generated constructor stub
	}

	public Friend(String followee, String follower, String friendshipName, int visitCount, String followeeProfilePath,
			String followerProfilePath, String followerProfileName, String followerProfilePhone, String gender,
			String address, Date birth) {
		super();
		this.followee = followee;
		this.follower = follower;
		this.friendshipName = friendshipName;
		this.visitCount = visitCount;
		this.followeeProfilePath = followeeProfilePath;
		this.followerProfilePath = followerProfilePath;
		this.followerProfileName = followerProfileName;
		this.followerProfilePhone = followerProfilePhone;
		this.gender = gender;
		this.address = address;
		this.birth = birth;
	}

	public String getFollowee() {
		return followee;
	}

	public void setFollowee(String followee) {
		this.followee = followee;
	}

	public String getFollower() {
		return follower;
	}

	public void setFollower(String follower) {
		this.follower = follower;
	}

	public String getFriendshipName() {
		return friendshipName;
	}

	public void setFriendshipName(String friendshipName) {
		this.friendshipName = friendshipName;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public String getFolloweeProfilePath() {
		return followeeProfilePath;
	}

	public void setFolloweeProfilePath(String followeeProfilePath) {
		this.followeeProfilePath = followeeProfilePath;
	}

	public String getFollowerProfilePath() {
		return followerProfilePath;
	}

	public void setFollowerProfilePath(String followerProfilePath) {
		this.followerProfilePath = followerProfilePath;
	}

	public String getFollowerProfileName() {
		return followerProfileName;
	}

	public void setFollowerProfileName(String followerProfileName) {
		this.followerProfileName = followerProfileName;
	}

	public String getFollowerProfilePhone() {
		return followerProfilePhone;
	}

	public void setFollowerProfilePhone(String followerProfilePhone) {
		this.followerProfilePhone = followerProfilePhone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Friend [followee=" + followee + ", follower=" + follower + ", friendshipName=" + friendshipName
				+ ", visitCount=" + visitCount + ", followeeProfilePath=" + followeeProfilePath
				+ ", followerProfilePath=" + followerProfilePath + ", followerProfileName=" + followerProfileName
				+ ", followerProfilePhone=" + followerProfilePhone + ", gender=" + gender + ", address=" + address
				+ ", birth=" + birth + "]";
	}

}
