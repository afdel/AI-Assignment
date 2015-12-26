package soton.ai.afdel.crossesandnoughts;

public class Move {

	int signPosition;
	Long signToPlay;
	
	
	public Move() {
	}
	
	public Move(int signPosition, Long signToPlay) {
		super();
		this.signPosition = signPosition;
		this.signToPlay = signToPlay;
	}

	public int getSignPosition() {
		return signPosition;
	}

	public void setSignPosition(int signPosition) {
		this.signPosition = signPosition;
	}

	public Long getSignToPlay() {
		return signToPlay;
	}

	public void setSignToPlay(Long signToPlay) {
		this.signToPlay = signToPlay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + signPosition;
		result = prime * result + ((signToPlay == null) ? 0 : signToPlay.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (signPosition != other.signPosition)
			return false;
		if (signToPlay == null) {
			if (other.signToPlay != null)
				return false;
		} else if (!signToPlay.equals(other.signToPlay))
			return false;
		return true;
	}
	
}
