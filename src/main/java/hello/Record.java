package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Record {

	String name;
	volatile String instagramFollowerCount;
	String picCount;

	String sumTopComments;
	String sumTopLikes;
	String averageComments;
	String averageLikes;
	String averageViews;
	String engagementRate; // (likes + comments / followers)
	String picLink;

	public Record() {

	}

	public Record(String name, String string) {
		this.name = name;
		double commentsCnt = 0;
		double followers = 0;
		double likesCnt = 0;
		double likes = 0;
		double comments = 0;

		List<String> commentsarr = new ArrayList<>();
		List<String> likesarr = new ArrayList<>();

		Pattern pattern = Pattern.compile("followed_by....count...[0-9]{1,10}");
		Matcher matcher = pattern.matcher(string);
		System.out.println("followers");
		while (matcher.find()) {
			String group = matcher.group();
			System.out.println(group);
			group = group.replaceAll("\\D", "");
			followers = Integer.parseInt(group);
			instagramFollowerCount = group;
		}

		pattern = Pattern.compile("meta property..og.image..content=(.*).jpg");
		matcher = pattern.matcher(string);
		while (matcher.find()) {
			String group = matcher.group();
			group = group.replace("meta property=\"og:image\" content=\"", "");
			picLink = group;
			System.err.println(group);

		}
		//
		System.out.println("coments");
		pattern = Pattern.compile("edge_media_to_comment.:{.count.:[0-9]{1,10}");
		matcher = pattern.matcher(string);
		while (matcher.find()) {
			commentsCnt++;
			String group = matcher.group();
			System.out.println(group);
			commentsarr.add(group);
			group = group.replaceAll("\\D", "");
			comments += Integer.parseInt(group);
		}
		picCount = "" + commentsCnt;

		System.out.println("likes");
		pattern = Pattern.compile("edge_liked_by.:{.count.:[0-9]{1,10}");
		matcher = pattern.matcher(string);
		while (matcher.find()) {
			likesCnt++;
			String group = matcher.group();
			System.out.println(group);
			likesarr.add(group);
			group = group.replaceAll("\\D", "");
			likes += Integer.parseInt(group);
		}
		sumTopComments = "" + comments;
		sumTopLikes = "" + likes;

		for (int i = 0; i < likesarr.size(); i++) {
			System.out.println(i + " " + likesarr.get(i) + " " + commentsarr.get(i));
		}

		double avgComments = comments / commentsCnt;
		double avgLikes = likes / likesCnt;

		double engagement = (avgLikes + avgComments) / (followers);
		engagement = engagement * 100;
		averageComments = "" + avgComments;
		averageLikes = "" + avgLikes;
		engagementRate = "" + engagement;

	}

	@Override
	public String toString() {
		return "Record [name=" + name + ", instagramFollowerCount=" + instagramFollowerCount + ", picCount=" + picCount
				+ ", sumTopComments=" + sumTopComments + ", sumTopLikes=" + sumTopLikes + ", averageComments="
				+ averageComments + ", averageLikes=" + averageLikes + ", averageViews=" + averageViews
				+ ", engagementRate=" + engagementRate + ", picLink=" + picLink + "]";
	}

	public String getName() {
		return name;
	}

	public String getPicLink() {
		return picLink;
	}

	public void setPicLink(String picLink) {
		this.picLink = picLink;
	}

	public String getPicCount() {
		return picCount;
	}

	public void setPicCount(String picCount) {
		this.picCount = picCount;
	}

	public String getSumTopComments() {
		return sumTopComments;
	}

	public void setSumTopComments(String sumTopComments) {
		this.sumTopComments = sumTopComments;
	}

	public String getSumTopLikes() {
		return sumTopLikes;
	}

	public void setSumTopLikes(String sumTopLikes) {
		this.sumTopLikes = sumTopLikes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstagramFollowerCount() {
		return instagramFollowerCount;
	}

	public void setInstagramFollowerCount(String instagramFollowerCount) {
		this.instagramFollowerCount = instagramFollowerCount;
	}

	public String getAverageComments() {
		return averageComments;
	}

	public void setAverageComments(String averageComments) {
		this.averageComments = averageComments;
	}

	public String getAverageLikes() {
		return averageLikes;
	}

	public void setAverageLikes(String averageLikes) {
		this.averageLikes = averageLikes;
	}

	public String getAverageViews() {
		return averageViews;
	}

	public void setAverageViews(String averageViews) {
		this.averageViews = averageViews;
	}

	public String getEngagementRate() {
		return engagementRate;
	}

	public void setEngagementRate(String engagementRate) {
		this.engagementRate = engagementRate;
	}

}
