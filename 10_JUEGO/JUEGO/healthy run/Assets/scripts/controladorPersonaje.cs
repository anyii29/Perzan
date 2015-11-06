using UnityEngine;
using System.Collections;

public class ControladorPersonaje : MonoBehaviour {
	
	public float fuerzaSalto = 100f;
	
	private bool enSuelo = true;
	public Transform comprobadorSuelo;
	private float comprobadorRadio = 0.07f;
	public LayerMask mascaraSuelo;
	
	private bool dobleSalto = false;
	
	private Animator animator;
	
	void Awake(){
		animator = GetComponent<Animator>();
	}
	
	// Use this for initialization
	void Start () {
		
	}
	
	void FixedUpdate(){
		enSuelo = Physics2D.OverlapCircle(comprobadorSuelo.position, comprobadorRadio, mascaraSuelo);
		animator.SetBool("isGrounded", enSuelo);
		if(enSuelo){
			dobleSalto = false;
		}
	}
	
	// Update is called once per frame
	void Update () {
		if((enSuelo || !dobleSalto) && Input.GetMouseButtonDown(0)){
			GetComponent<Rigidbody2D>().velocity = new Vector2(GetComponent<Rigidbody2D>().velocity.x, fuerzaSalto);
			//rigidbody2D.AddForce(new Vector2(0, fuerzaSalto));
			if(!dobleSalto && !enSuelo){
				dobleSalto = true;
			}
		}
	}
}
