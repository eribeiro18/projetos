
//sealed informa que a abstração é selada e o permits é qual classe pode extender tal abstração 
//após o permits pode ter mais que uma classe sendo separadas por virgula conforme abaixo
abstract sealed class Carnivoro permits Leao, Tigre{
	
	public void euSou() {
		System.out.println(" Eu sou carnívoro.\n");
	}
}

abstract sealed class Herbivoro permits Coelho{
	
	public void euSou() {
		System.out.println(" Eu sou herbívoro.\n");
	}
}

//deve ser sealed, non-sealed ou final 
non-sealed class Leao extends Carnivoro {
	
	public void euSou() {
		System.out.println(" eu sou um leão, ");
		super.euSou();
	}
}

non-sealed class Tigre extends Carnivoro {
	
	public void euSou() {
		System.out.println(" eu sou um tigre, ");
		super.euSou();
	}
}

final class Coelho extends Herbivoro {
	
	public void euSou() {
		System.out.println(" eu sou um Coelho, ");
		super.euSou();
	}
}

//exemplo abaixo é sem sentido, por isso foi criado as classes seladas para impedir implementações sem nexo
//conforme exemplificado acima
//class Geladeira extends Carnivoro {
//	
//	public void euSou() {
//		System.out.println(" eu sou uma geladeira, ");
//		super.euSou();
//	}
//}

public class ClassesSeladasEx {

	public static void main(String[] args) {
		
		Leao l = new Leao();
		l.euSou();
		Coelho c = new Coelho();
		c.euSou();
		
	}

}
