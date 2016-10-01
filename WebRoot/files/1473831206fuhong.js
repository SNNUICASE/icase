var checkFromat = function(){
	return true;
}

var parseObject = function(str){

	var list = str.split(/\n\n/);
	var res = [];

	list.map(li => {
		var item = li.split('\n');
		var animals = [];

		for(var i=2; i<item.length; i++){
			
			var buf = item[i].split(' ');
			var animal = {
				id:buf[0],
				x:parseInt(buf[1]),
				y:parseInt(buf[2]),
				offset_x:buf[3]?parseInt(buf[3]):0,
				offset_y:buf[4]?parseInt(buf[4]):0
			}

			animals.push(animal);
		}

		var obj = {
			id:item[0],
			time:item[1],
			animals:animals
		}
		res.push(obj)

	});

	console.log(list[0].id);

	return res;
}

var compute = function(list,id){
	var offset = 0
	var res = {};

	list.map((item,index) => {
		if(item.id === id){
			offset = index;
		}
	});
	console.log(id);
	console.log(list);
	console.log(offset);

	for(var i=offset; i>=0; i--){
		var animals = list[i].animals;
		
		animals.map(animal => {
			if(res[animal.id]){

				res[animal.id].x = animal.x;
				res[animal.id].y = animal.y;

				if(animal.offset_x === 0 && animal.offset_y === 0){
					res[animal.id].x = res[animal.id].offset_x + animal.x;
					res[animal.id].y = res[animal.id].offset_y + animal.y;
				}else{
					res[animal.id].offset_x += animal.offset_x;
					res[animal.id].offset_y += animal.offset_y;
				}
			}else{
				res[animal.id] = {
					offset_x:animal.offset_x,
					offset_y:animal.offset_y,
					x:animal.x,
					y:animal.y
				}
			}
		});
	}

	return res;
}

var str = 
`e4e87cb2-8e9a-4749-abb6-26c59344dfee
2016/09/02 22:30:46
cat1 10 9

351055db-33e6-4f9b-bfe1-16f1ac446ac1
2016/09/02 22:30:52
cat1 10 9 2 -1
cat2 2 3

dcfa0c7a-5855-4ed2-bc8c-4accae8bd155
2016/09/02 22:31:02
cat1 12 8 3 4`


if(checkFromat(str)){
	var list = parseObject(str);
	var id = 'dcfa0c7a-5855-4ed2-bc8c-4accae8bd155';
	// id = '351055db-33e6-4f9b-bfe1-16f1ac446ac1';

	var res = compute(list,id);
	console.table(res);
}