package com.juan.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.juan.cursomc.domain.Categoria;
import com.juan.cursomc.domain.Cidade;
import com.juan.cursomc.domain.Cliente;
import com.juan.cursomc.domain.Endereco;
import com.juan.cursomc.domain.Estado;
import com.juan.cursomc.domain.Produto;
import com.juan.cursomc.domain.enums.TipoCliente;
import com.juan.cursomc.repositories.CategoriaRepository;
import com.juan.cursomc.repositories.CidadeRepository;
import com.juan.cursomc.repositories.ClienteRepository;
import com.juan.cursomc.repositories.EnderecoRepository;
import com.juan.cursomc.repositories.EstadoRepository;
import com.juan.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired 
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Instrumento");
		Categoria cat2 = new Categoria(null, "Acessório");
		
		Produto p1 = new Produto(null, "Guitarra", 3800.00);
		Produto p2 = new Produto(null, "Amplificador", 3500.00);
		Produto p3 = new Produto(null, "Pedaleira", 2700.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().addAll(Arrays.asList(p2, p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Rio Grande do Sul");
		Estado est2 = new Estado(null, "Rio de Janeiro");
		
		Cidade c1 = new Cidade(null, "Porto Alegre", est1);
		Cidade c2 = new Cidade(null, "Rio de Janeiro", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2));
		
		Cliente cli1 = new Cliente(null, "Juan Andrade", "juanandrade@gmail.com", "123456789-10", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("98765-4321", "12345-6789"));
		
		Endereco end1 = new Endereco(null, "Av. Ipiranga", "8000", "Apto 508", "Centro", "90000-987", cli1, c1);
		Endereco end2 = new Endereco(null, "Av. Brasil", "170", "Apto 817", "Brasiliense", "10000-123", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
	}

}
















