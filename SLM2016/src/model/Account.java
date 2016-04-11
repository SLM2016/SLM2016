package model;

public class Account {
	private int mId = -1;
	private String mUserName = "";
	private String mNickName = "";
	private String mEmail = "";
	private int mPhone = -1;
	private String mCompany ="";
	private String mDepartment ="";
	private String mPosition ="";
	
	public Account (int id, String username){
		mUserName = username;
		mId = id;
	}
	
	public Account (String username) {
		if(username != null && username != ""){
			mUserName = username;
		}else {
			throw new RuntimeException();
		}
	}
	
	public int getId() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public String getUserName() {
		return mUserName;
	}

	public void setUserName(String mUserName) {
		this.mUserName = mUserName;
	}

	public String getNickName() {
		return mNickName;
	}

	public void setNickName(String mNickName) {
		this.mNickName = mNickName;
	}

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public int getPhone() {
		return mPhone;
	}

	public void setPhone(int mPhone) {
		this.mPhone = mPhone;
	}

	public String getCompany() {
		return mCompany;
	}

	public void setCompany(String mCompany) {
		this.mCompany = mCompany;
	}

	public String getDepartment() {
		return mDepartment;
	}

	public void setDepartment(String mDepartment) {
		this.mDepartment = mDepartment;
	}

	public String getPosition() {
		return mPosition;
	}

	public void setPosition(String mPosition) {
		this.mPosition = mPosition;
	}

}
