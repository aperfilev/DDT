Ⓗ▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂ 

#@EXP_UNARY《
  ►#?AST_STRUCTURE_EXPECTED!【this●ExpThis】●
  ►#?AST_STRUCTURE_EXPECTED!【super●ExpSuper】●
  ►#?AST_STRUCTURE_EXPECTED!【null●ExpNull】●
  ►#?AST_STRUCTURE_EXPECTED!【true●ExpLiteralBool】●
  ►#?AST_STRUCTURE_EXPECTED!【false●ExpLiteralBool】●
  ►#?AST_STRUCTURE_EXPECTED!【$●ExpArrayLength】●
  ►#?AST_STRUCTURE_EXPECTED!【'"'●ExpLiteralChar】●
  ►#?AST_STRUCTURE_EXPECTED!【#@X《12●123_45Lu》●Integer】●
  ►#?AST_STRUCTURE_EXPECTED!【#@X《123.0F●.456E12●0x25_AD_3FP+1》●Float】●
  ►#?AST_STRUCTURE_EXPECTED!【#@X《"abc"●r"inline"q{ TOKEN string }`sfds`》●String】●
  
  ►#?AST_STRUCTURE_EXPECTED!【__FILE__●ExpLiteralString】●
  ►#?AST_STRUCTURE_EXPECTED!【__LINE__●ExpLiteralInteger】●
  ►#?AST_STRUCTURE_EXPECTED!【 [1, .456E12, 0x25_AD_3FP+1] ●ExpLiteralArray(Integer Float Float)】●
  ►#?AST_STRUCTURE_EXPECTED!【 [12345 : true, 66.6 : false, "asdfd" : "false"]●
  ExpLiteralMapArray(MapEntry(Integer Bool) MapEntry(Float Bool) MapEntry(String String))】●
  
  ►#?AST_STRUCTURE_EXPECTED!【assert(2 + 2 == true, "error")●ExpAssert(* String)】●
  ►#?AST_STRUCTURE_EXPECTED!【mixin("2 + " ~ "2")●ExpMixinString(*)】●
  ►#?AST_STRUCTURE_EXPECTED!【import("testdata/samples.txt")●ExpImportString(String)】●
  
¤》
#@EXP_UNARY__LITE《
  ►#?AST_STRUCTURE_EXPECTED!【42●Integer】●
¤》

#@EXP_UNARY_REFS《
  ►#?AST_STRUCTURE_EXPECTED!【foo●ExpReference(RefIdentifier)】●
  
  ►#?AST_STRUCTURE_EXPECTED!【#error(TYPE_AS_EXP_VALUE)《int》●ExpReference(RefPrimitive)】●
  ►#?AST_STRUCTURE_EXPECTED!【#error(TYPE_AS_EXP_VALUE)《int[]》●ExpReference(RefTypeDynArray(RefPrimitive))】●
  ►#?AST_STRUCTURE_EXPECTED!【#error(TYPE_AS_EXP_VALUE)《int[foo]》●ExpReference(RefIndexing(RefPrimitive ?))】●
¤》
  
#@EXP_UNARY_WITHREFS《
  ►#@EXP_UNARY●
  ►#@EXP_UNARY_REFS●
¤》
#@EXP_UNARY_WITHREFS__LITE《
  ►#?AST_STRUCTURE_EXPECTED!【42●Integer】●
  ►#?AST_STRUCTURE_EXPECTED!【#error(TYPE_AS_EXP_VALUE)《int[foo]》●ExpReference(RefIndexing(RefPrimitive ?))】●
¤》

#@EXP_OROR《
  ►#@EXP_UNARY●
  ►#?AST_STRUCTURE_EXPECTED!【4 / 6●ExpInfix(? ?)】●
  ►#?AST_STRUCTURE_EXPECTED!【1 + 2●ExpInfix(? ?)】●
  ►#?AST_STRUCTURE_EXPECTED!【1 << 16●ExpInfix(? ?)】●
  ►#?AST_STRUCTURE_EXPECTED!【0xFF & 123●ExpInfix(? ?)】●
  ►#?AST_STRUCTURE_EXPECTED!【0xFF | 0xAA●ExpInfix(? ?)】●
  ►#?AST_STRUCTURE_EXPECTED!【1 > "2" && 3●ExpInfix(ExpInfix(? String) ?)】●
  ►#?AST_STRUCTURE_EXPECTED!【2 || "3" < 4●ExpInfix(? ExpInfix(String ?))】●
  
  ►#@EXP_OROR__LITE●
¤》
#@EXP_OROR__LITE《
  ►#?AST_STRUCTURE_EXPECTED!【2 ~ [1, 0xFF, "3"] == null ~ [123 : "entry"] || assert(2 < "four" + length)●
ExpInfix(
  ExpInfix(
    ExpInfix(Integer ExpLiteralArray(* * *))  
    ExpInfix(ExpNull ExpLiteralMapArray(*))
  )
  ExpAssert(ExpInfix(Integer ExpInfix(String ExpReference(RefIdentifier))))
)】●
¤》

#@EXP_CONDITIONAL《
  ►#@EXP_OROR●
  ►#@EXP_CONDITIONAL__LITE●
¤》
#@EXP_CONDITIONAL__LITE《
  ►#?AST_STRUCTURE_EXPECTED!【false ? 123 : 456●ExpConditional(Bool Integer Integer)】●
¤》

#@EXP_ASSIGN《
  ►#@EXP_CONDITIONAL●
  ►#?AST_STRUCTURE_EXPECTED!【this = super += null●ExpInfix(ExpThis ExpInfix(ExpSuper ExpNull))】●
¤》
#@EXP_ASSIGN__LITE《
  ►#@EXP_CONDITIONAL__LITE●
  ►#?AST_STRUCTURE_EXPECTED!【this = super += null●ExpInfix(ExpThis ExpInfix(ExpSuper ExpNull))】●
¤》

#@EXP_COMMA《
  ►#@EXP_ASSIGN●
  ►#?AST_STRUCTURE_EXPECTED!【12,"asd"●ExpInfix(Integer String)】●
¤》

#@EXP_ANY《#@EXP_COMMA》
#@EXP_ANY__LITE《
  ►#@EXP_ASSIGN__LITE●
  ►#?AST_STRUCTURE_EXPECTED!【this = super += null●ExpInfix(ExpThis ExpInfix(ExpSuper ExpNull))】●
¤》
