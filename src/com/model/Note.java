package com.model;



public class Note {
    protected int userid;
    protected String startdate;
    protected String enddate;
    protected String reminderdate;
    protected String status;
    protected String tag;
    protected String description;
    protected int notebookid;
    protected String notebookname;


    public String getNotebookname() {
		return notebookname;
	}
	public void setNotebookname(String notebookname) {
		this.notebookname = notebookname;
	}
	public Note() {}
    public Note(int userid,String startdate,String enddate,String reminderdate,String status,String tag,String description)
    {
    	this.userid=userid;
        this.startdate=startdate;
        this.enddate=enddate;
        this.reminderdate=reminderdate;
        this.status=status;
        this.tag=tag;
        this.description=description;    	
    }
    public Note(String startdate,String enddate,String reminderdate,String status,String tag,String description,int notebookid) {
        super();
        
        this.startdate=startdate;
        this.enddate=enddate;
        this.reminderdate=reminderdate;
        this.status=status;
        this.tag=tag;
        this.description=description;
        this.notebookid=notebookid;
        
    }
    public Note(int userid,String startdate,String enddate,String reminderdate,String status,String tag,String description,int notebookid,String notebookname) {
        super();
        this.userid=userid;
        this.startdate=startdate;
        this.enddate=enddate;
        this.reminderdate=reminderdate;
        this.status=status;
        this.tag=tag;
        this.description=description;
        this.notebookid=notebookid;
        this.notebookname=notebookname;
        
    }
    
    public Note(int userid,String startdate,String enddate,String reminderdate,String status,String tag,String description,int notebookid) {
        super();
        this.userid=userid;
        this.startdate=startdate;
        this.enddate=enddate;
        this.reminderdate=reminderdate;
        this.status=status;
        this.tag=tag;
        this.description=description;
        this.notebookid=notebookid;
        
    }

    
    public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getReminderdate() {
		return reminderdate;
	}

	public void setReminderdate(String reminderdate) {
		this.reminderdate = reminderdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNotebookid() {
		return notebookid;
	}

	public void setNotebookid(int notebookid) {
		this.notebookid = notebookid;
	}

	
	

    
    
}