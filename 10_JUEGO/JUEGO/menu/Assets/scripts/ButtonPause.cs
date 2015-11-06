using UnityEngine;
using System.Collections;

public class ButtonPause : MonoBehaviour {
	private bool paused;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update() {
	}
	public void PauseGame()
	{
		paused = !paused;
		if (paused == true) {
			paused = false;
		}
		if (paused == false) {
			paused = true;
		}

	}
}
