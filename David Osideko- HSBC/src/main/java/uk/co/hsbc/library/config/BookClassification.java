package uk.co.hsbc.library.config;

import lombok.Getter;

@Getter
public enum BookClassification {

	A ("General Works"),
	B ("Philosophy. Psychology. Religion"),
	C ("Auxiliary Sciences of History"),
	D ("World History and History of Europe, Asia, Africa, Australia, New Zealand, etc."),
	E ("History of America"),
	F ("History of the Americas"),
	G ("Geography, Anthropology, and Recreation"),
	H ("Social Sciences"),
	J ("Political Science"),
	K ("Law"),
	L ("Education"),
	M ("Music"),
	N ("Fine Arts"),
	P ("Language and Literature"),
	Q ("Science"),
	R ("Medicine"),
	S ("Agriculture"),
	T ("Technology"),
	U ("Military Science"),
	V ("Naval Science"),
	Z ("Bibliography, Library Science, and General Information Resources");

	private String description;
	BookClassification(String description) {
		this.description = description;
	}
}
